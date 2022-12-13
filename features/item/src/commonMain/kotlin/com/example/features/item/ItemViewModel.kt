package com.example.features.item

import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class ItemScreenUiState(
    val items: List<Item>
) {
    companion object {
        val Empty = ItemScreenUiState(emptyList())
    }
}

class ItemViewModel(
    getItem: GetItemListUseCase,
    private val insertItem: InsertItemUseCase
) {

    private val tmpCoroutineScope = CoroutineScope(Dispatchers.Main.immediate)

    val uiState: StateFlow<ItemScreenUiState> = getItem()
        .map(::ItemScreenUiState)
        .stateIn(tmpCoroutineScope, SharingStarted.WhileSubscribed(5000), ItemScreenUiState.Empty)

    fun insertItem() {
        val tmpItem = Item("tmp", "tmp")
        insertItem(tmpItem)
    }
}