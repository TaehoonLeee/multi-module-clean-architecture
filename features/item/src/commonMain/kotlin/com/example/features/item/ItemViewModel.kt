package com.example.features.item

import com.example.common.ViewModel
import com.example.domain.interactor.ClearItemUseCase
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ItemViewModel : ViewModel(), KoinComponent {

    private val getItem: GetItemListUseCase by inject()
    private val clearItem: ClearItemUseCase by inject()
    private val insertItem: InsertItemUseCase by inject()

    val uiState = getItem()
        .stateIn(coroutineScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertItem() {
        insertItem(Item("tmp", "tmp"))
    }

    fun clear() {
        clearItem()
    }

}