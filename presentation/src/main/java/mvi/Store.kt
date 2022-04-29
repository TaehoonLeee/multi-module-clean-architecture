package mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Store<INTENT, STATE, MESSAGE> {

    val state: StateFlow<STATE>

    suspend fun accept(intent: INTENT)
}

abstract class ViewModelStore<INTENT, STATE, MESSAGE> : Store<INTENT, STATE, MESSAGE>, ViewModel() {

    abstract val initialState: STATE

    private val executor = DefaultExecutor<INTENT, MESSAGE>(onIntent = { onIntent(it) })
    private val intentStateMachine: Channel<INTENT> = Channel()
    private val messageStateMachine: Channel<MESSAGE> = Channel()

    init {
        executor.init {
            viewModelScope.launch {
                messageStateMachine.send(it)
            }
        }

        viewModelScope.launch {
            intentStateMachine.consumeEach(executor::executeIntent)
        }
    }

    override val state: StateFlow<STATE> by lazy {
        messageStateMachine
            .receiveAsFlow()
            .runningFold(initialState, ::reduce)
            .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)
    }

    override suspend fun accept(intent: INTENT) = intentStateMachine.send(intent)

    protected abstract fun Executor<INTENT, MESSAGE>.onIntent(intent: INTENT)
    protected abstract fun reduce(state: STATE, message: MESSAGE): STATE
}