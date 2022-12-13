package com.example.features.item

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.example.domain.model.Item
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface ItemComponent {
    val state: Value<ItemComponentState>

    fun onInsertItem()

    data class ItemComponentState(
        val items: List<Item>
    )
}

class ItemComponentImpl(
    componentContext: ComponentContext
) : ItemComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel = instanceKeeper.getOrCreate {
        ItemViewModel(get(), get())
    }

    override val state: Value<ItemComponent.ItemComponentState> = viewModel.state
    override fun onInsertItem() = viewModel.insertItem()
}