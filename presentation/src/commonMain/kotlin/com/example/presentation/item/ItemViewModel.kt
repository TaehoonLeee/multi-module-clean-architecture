package com.example.presentation.item

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import com.example.presentation.util.asValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.map

class ItemViewModel(
    getItem: GetItemListUseCase,
    private val insertItem: InsertItemUseCase
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    val state: Value<ItemComponent.ItemComponentState> = getItem()
        .map { ItemComponent.ItemComponentState(it) }
        .asValue(ItemComponent.ItemComponentState(emptyList()), viewModelScope)

    fun insertItem() {
        insertItem(Item("tmp", "tmp"))
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }
}