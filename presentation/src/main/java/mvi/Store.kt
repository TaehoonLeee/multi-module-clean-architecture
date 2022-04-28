package mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Store<INTENT, STATE, MESSAGE> {

    val state: StateFlow<STATE>

    suspend fun accept(intent: INTENT)
}

class DefaultStore<INTENT, STATE, MESSAGE>(
    initialState: STATE,
    private val coroutineScope: CoroutineScope,
    private val executor: Executor<INTENT, MESSAGE>,
    private val reducer: Reducer<STATE, MESSAGE>
) : Store<INTENT, STATE, MESSAGE> {

    init {
        executor.init {
            coroutineScope.launch {
                messageStateMachine.send(it)
            }
        }

        coroutineScope.launch {
            intentStateMachine.consumeEach(executor::executeIntent)
        }
    }

    private val intentStateMachine: Channel<INTENT> = Channel()
    private val messageStateMachine: Channel<MESSAGE> = Channel()

    override val state: StateFlow<STATE> = messageStateMachine
        .receiveAsFlow()
        .runningFold(initialState, reducer::reduce)
        .stateIn(coroutineScope, SharingStarted.Eagerly, initialState)

    override suspend fun accept(intent: INTENT) = intentStateMachine.send(intent)

    companion object {
        fun <INTENT, STATE, MESSAGE> create(
            initialState: STATE,
            coroutineScope: CoroutineScope,
            executor: Executor<INTENT, MESSAGE>,
            reducer: Reducer<STATE, MESSAGE>
        ): Store<INTENT, STATE, MESSAGE> = DefaultStore(
            initialState, coroutineScope, executor, reducer
        )
    }
}