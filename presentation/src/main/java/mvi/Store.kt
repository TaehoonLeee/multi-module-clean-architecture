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

abstract class ViewModelStore<INTENT, STATE, MESSAGE>(
    initialState: STATE,
    sharingStarted: SharingStarted = SharingStarted.Eagerly
) : Store<INTENT, STATE, MESSAGE>, ViewModel() {

    private val intentStateMachine: Channel<INTENT> = Channel()
    private val messageStateMachine: Channel<MESSAGE> = Channel()

    init {
        viewModelScope.launch {
            intentStateMachine.consumeEach(::onIntent)
        }
    }

    override val state: StateFlow<STATE> = messageStateMachine
        .receiveAsFlow()
        .runningFold(initialState, ::reduce)
        .stateIn(viewModelScope, sharingStarted, initialState)

    override suspend fun accept(intent: INTENT) = intentStateMachine.send(intent)
    protected fun dispatch(message: MESSAGE) {
        viewModelScope.launch {
            messageStateMachine.send(message)
        }
    }

    protected abstract fun onIntent(intent: INTENT)
    protected abstract fun reduce(state: STATE, message: MESSAGE): STATE
}