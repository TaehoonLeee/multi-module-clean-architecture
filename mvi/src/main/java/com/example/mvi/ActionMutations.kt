package com.example.mvi

import kotlinx.coroutines.flow.*

class TransformationContext<Action : Any>(
	val flow: Flow<Action>
)

fun <Action : Any, State : Any> Flow<Action>.toMutationStream(
	keySelector: (Action) -> String = Any::defaultKeySelector,
	transform: context(TransformationContext<Action>) () -> Flow<Mutation<State>>
): Flow<Mutation<State>> = splitByType(
	typeSelector = { it },
	keySelector = keySelector,
	transform = transform
)

fun <Input : Any, Selector : Any, Output : Any> Flow<Input>.splitByType(
	typeSelector: (Input) -> Selector,
	keySelector: (Selector) -> String = Any::defaultKeySelector,
	transform: context(TransformationContext<Selector>) () -> Flow<Output>
): Flow<Output> = channelFlow mutationFlow@{
	val keysToFlowHolders = mutableMapOf<String, FlowHolder<Selector>>()
	this@splitByType
		.collect { item ->
			val selected = typeSelector(item)
			val flowKey = keySelector(selected)
			when (val existingHolder = keysToFlowHolders[flowKey]) {
				null -> {
					val holder = FlowHolder(selected)
					keysToFlowHolders[flowKey] = holder
					val context = TransformationContext(holder.exposedFlow)
					val mutationFlow = transform(context)
					channel.send(mutationFlow)
				}
				else -> {
					existingHolder.internalSharedFlow.subscriptionCount.first { it > 0 }
					existingHolder.internalSharedFlow.emit(selected)
				}
			}
		}
}.flatMapMerge(
	concurrency = Int.MAX_VALUE,
	transform = { it }
)

private data class FlowHolder<Action>(
	val firstEmission: Action,
) {
	val internalSharedFlow: MutableSharedFlow<Action> = MutableSharedFlow()
	val exposedFlow: Flow<Action> = internalSharedFlow.onStart { emit(firstEmission) }
}

private fun Any.defaultKeySelector(): String = this::class.simpleName
	?: throw IllegalArgumentException(
		"Only well defined classes can be split or specify a different key selector"
	)