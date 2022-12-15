package com.example.features.item

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.example.common.valueIn
import com.example.domain.interactor.ClearItemUseCase
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import com.example.features.item.ItemComponent.ItemComponentState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ItemViewModel(
    getItem: GetItemListUseCase,
    private val clearItem: ClearItemUseCase,
    private val insertItem: InsertItemUseCase
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    val state: Value<ItemComponentState> = getItem()
        .map(::ItemComponentState)
        .valueIn(ItemComponentState(emptyList()), SharingStarted.Eagerly, viewModelScope)

    fun insertItem() {
        insertItem(Item("tmp", "tmp"))
    }

    fun clear() {
        clearItem()
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}