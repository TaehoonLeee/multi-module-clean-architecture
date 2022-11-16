package com.example.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface ActionStateProducer<Action : Any, State : Any> {
	val process: (Action) -> Unit

	val state: StateFlow<State>
}

fun <Action : Any, State : Any> ViewModel.actionStateProducer(
	initialState: State,
	started: SharingStarted = SharingStarted.WhileSubscribed(5000),
	mutationFlows: List<Flow<Mutation<State>>> = listOf(),
	scope: CoroutineScope = viewModelScope,
	actionTransform: (Flow<Action>) -> Flow<Mutation<State>>
): ActionStateProducer<Action, State> = object : ActionStateProducer<Action, State> {
	val actions = MutableSharedFlow<Action>()

	override val state: StateFlow<State> = scope.produceState(
		initial = initialState,
		started = started,
		mutationFlows = mutationFlows + actionTransform(actions)
	)

	override val process: (Action) -> Unit = { action ->
		scope.launch {
			actions.subscriptionCount.first { it > 0 }
			actions.emit(action)
		}
	}
}
