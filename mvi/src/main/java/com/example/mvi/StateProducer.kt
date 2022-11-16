package com.example.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

fun <State : Any> CoroutineScope.produceState(
	initial: State,
	started: SharingStarted = SharingStarted.WhileSubscribed(5000),
	mutationFlows: List<Flow<Mutation<State>>>
): StateFlow<State> {
	var seed = initial

	return flow {
		emitAll(
			merge(*mutationFlows.toTypedArray())
				.scan(seed) { state, mutation -> mutation(state) }
				.onEach { seed = it }
		)
	}.stateIn(
		scope = this,
		started = started,
		initialValue = seed
	)
}