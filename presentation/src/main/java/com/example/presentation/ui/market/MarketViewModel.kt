package com.example.presentation.ui.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.kumnan.aos.apps.domain.interactor.GetItemListUseCase
import org.kumnan.aos.apps.domain.interactor.InsertItemUseCase
import org.kumnan.aos.apps.domain.model.Item
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(
    getItemUseCase: GetItemListUseCase,
    private val insertItemUseCase: InsertItemUseCase
) : ViewModel() {

    val items = getItemUseCase().stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000), null
    )

    fun insertItem(item: Item) = viewModelScope.launch {
        insertItemUseCase(item)
    }
}