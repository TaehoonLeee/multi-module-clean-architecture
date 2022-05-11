package com.example.presentation.ui.item

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.domain.model.Item
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mvi.Executor
import mvi.ViewModelStore
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    private val getItem: GetItemListUseCase,
    private val insertItem: InsertItemUseCase,
) : ViewModel() {

    val itemList = getItem()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun onButtonClick(item: Item) = viewModelScope.launch {
        insertItem(item)
    }
}