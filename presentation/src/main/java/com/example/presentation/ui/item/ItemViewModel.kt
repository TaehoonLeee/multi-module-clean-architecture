package com.example.presentation.ui.item

import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mvi.ViewModelStore
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    initialState: ItemState,
    private val getItemUseCase: GetItemListUseCase,
    private val insertItemUseCase: InsertItemUseCase,
) : ViewModelStore<ItemIntent, ItemState, ItemMessage>(initialState) {

    init {
    	viewModelScope.launch {
    	    accept(ItemIntent.ObserveItems)
        }
    }

    override fun onIntent(intent: ItemIntent) {
        when (intent) {
            is ItemIntent.ObserveItems -> getItemUseCase()
                .onEach { dispatch(ItemMessage.Fetched(it)) }
                .launchIn(viewModelScope)

            is ItemIntent.InsertItem -> viewModelScope.launch {
                insertItemUseCase(intent.item)
            }
        }
    }

    override fun reduce(state: ItemState, message: ItemMessage): ItemState = when (message) {
        is ItemMessage.Fetched -> state.copy(items = message.data)
    }
}