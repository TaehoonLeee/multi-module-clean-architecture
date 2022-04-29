package mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

interface Store<INTENT, STATE, MESSAGE> {

    val state: StateFlow<STATE>

    suspend fun accept(intent: INTENT)
}

class ViewModelStore<INTENT, STATE, MESSAGE>(
    initialState: STATE,
    viewModelScope: CoroutineScope,
    private val executor: Executor<INTENT, MESSAGE>,
    private val reducer: Reducer<STATE, MESSAGE>
) : Store<INTENT, STATE, MESSAGE> {

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

    private val intentStateMachine: Channel<INTENT> = Channel()
    private val messageStateMachine: Channel<MESSAGE> = Channel()

    override val state: StateFlow<STATE> = messageStateMachine
        .receiveAsFlow()
        .runningFold(initialState, reducer::reduce)
        .stateIn(viewModelScope, SharingStarted.Eagerly, initialState)

    override suspend fun accept(intent: INTENT) = intentStateMachine.send(intent)

    companion object {
        fun <INTENT, STATE, MESSAGE> ViewModel.create(
            initialState: STATE,
            onIntent: Executor<INTENT, MESSAGE>.(INTENT) -> Unit,
            reduce: (STATE, MESSAGE) -> STATE,
        ): Store<INTENT, STATE, MESSAGE> = ViewModelStore(
            initialState, viewModelScope, DefaultExecutor(onIntent), reduce
        )
    }
}