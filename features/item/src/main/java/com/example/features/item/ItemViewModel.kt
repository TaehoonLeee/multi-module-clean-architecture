package com.example.features.item

import androidx.lifecycle.ViewModel
import com.example.domain.interactor.ClearItemUseCase
import com.example.domain.interactor.GetItemListUseCase
import com.example.domain.interactor.InsertItemUseCase
import com.example.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ItemViewModel @Inject constructor(
    initialState: ItemState,
    getItemUseCase: GetItemListUseCase,
    clearItemUseCase: ClearItemUseCase,
    insertItemUseCase: InsertItemUseCase
) : ViewModel() {

    private val stateProducer = actionStateProducer(
        initialState = initialState,
        mutationFlows = listOf(
            getItemUseCase().map {
                mutation { copy(items = it) }
            }
        ),
        actionTransform = {
            onAction<ItemAction.InsertItem> {
                flow.map {
                    insertItemUseCase(it.item)
                    Mutations.identity()
                }
            }
            onAction<ItemAction.ClearItem> {
                flow.map {
                    clearItemUseCase()
                    Mutations.identity()
                }
            }
        }
    )

    val uiState = stateProducer.state
    val process = stateProducer.process
}