package com.example.presentation.ui.market

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mvi.Executor
import mvi.ViewModelStore
import org.kumnan.aos.apps.domain.interactor.GetItemListUseCase
import org.kumnan.aos.apps.domain.interactor.InsertItemUseCase
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    override val initialState: MarketState,
    private val getItemUseCase: GetItemListUseCase,
    private val insertItemUseCase: InsertItemUseCase,
) : ViewModelStore<MarketIntent, MarketState, MarketMessage>() {

    init {
    	viewModelScope.launch {
    	    accept(MarketIntent.ObserveItems)
        }
    }

    override fun Executor<MarketIntent, MarketMessage>.onIntent(intent: MarketIntent) {
        when (intent) {
            is MarketIntent.ObserveItems -> getItemUseCase()
                .onEach { dispatch(MarketMessage.Fetched(it)) }
                .launchIn(viewModelScope)

            is MarketIntent.InsertItem -> viewModelScope.launch {
                insertItemUseCase(intent.item)
            }
        }
    }

    override fun reduce(state: MarketState, message: MarketMessage): MarketState = when (message) {
        is MarketMessage.Fetched -> state.copy(items = message.data)
    }
}