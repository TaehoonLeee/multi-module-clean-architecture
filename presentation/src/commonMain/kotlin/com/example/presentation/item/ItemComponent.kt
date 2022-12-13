package com.example.presentation.item

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.example.domain.model.Item

interface ItemComponent {
    val state: Value<ItemComponentState>

    fun onInsertItem()

    data class ItemComponentState(
        val items: List<Item>
    )
}

class ItemComponentImpl(
    componentContext: ComponentContext
) : ItemComponent, ComponentContext by componentContext {

    private val viewModel = instanceKeeper.getOrCreate {
        ItemViewModel()
    }

    override val state: Value<ItemComponent.ItemComponentState> = viewModel.state
}