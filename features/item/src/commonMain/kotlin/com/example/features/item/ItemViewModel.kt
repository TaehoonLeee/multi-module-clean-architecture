package com.example.features.item

import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import com.example.features.item.ItemComponent.ItemComponentState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ItemViewModel(
    getItem: GetItemListUseCase,
    private val insertItem: InsertItemUseCase
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

    val state: Value<ItemComponentState> = getItem()
        .map(::ItemComponentState)
        .valueIn(ItemComponentState(emptyList()), SharingStarted.Eagerly, viewModelScope)

    fun insertItem() {
        insertItem(Item("tmp", "tmp"))
    }

    override fun onDestroy() {
        viewModelScope.cancel()
    }

    private fun <T: Any> Flow<T>.valueIn(
        initial: T,
        started: SharingStarted,
        coroutineScope: CoroutineScope
    ): Value<T> = object : Value<T>() {

        private val backing: StateFlow<T> = stateIn(coroutineScope, started, initial)
        override val value: T
            get() = backing.value

        override fun subscribe(observer: (T) -> Unit) {
            coroutineScope.launch {
                collect(observer)
            }
        }

        override fun unsubscribe(observer: (T) -> Unit) = Unit

    }
}