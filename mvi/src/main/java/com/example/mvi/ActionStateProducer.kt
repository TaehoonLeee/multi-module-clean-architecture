package com.example.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ActionTransformBuilder<Action : Any, State: Any> {
	val actionHandlers = ArrayList<(Flow<Action>) -> Flow<Mutation<State>>>()

	inline fun <reified T: Action> onAction(
		noinline block: context(TransformationContext<T>) T.() -> Flow<Mutation<State>>
	) {
		actionHandlers += { action ->
			action.filterIsInstance<T>().toMutationStream(transform = block)
		}
	}
}

interface ActionStateProducer<Action : Any, State : Any> {
	val process: (Action) -> Unit

	val state: StateFlow<State>
}

fun <Action : Any, State : Any> ViewModel.actionStateProducer(
	initialState: State,
	started: SharingStarted = SharingStarted.WhileSubscribed(5000),
	mutationFlows: List<Flow<Mutation<State>>> = listOf(),
	scope: CoroutineScope = viewModelScope,
	actionTransform: ActionTransformBuilder<Action, State>.() -> Unit
): ActionStateProducer<Action, State> = object : ActionStateProducer<Action, State> {
	val actions = MutableSharedFlow<Action>()
	val builder = ActionTransformBuilder<Action, State>()

	init {
		actionTransform(builder)
	}

	override val state: StateFlow<State> = scope.produceState(
		initial = initialState,
		started = started,
		mutationFlows = mutationFlows + builder.actionHandlers.map { it(actions) }
	)

	override val process: (Action) -> Unit = { action ->
		scope.launch {
			actions.subscriptionCount.first { it > 0 }
			actions.emit(action)
		}
	}
}
