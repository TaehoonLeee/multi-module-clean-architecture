package com.example.features.gallery

import app.cash.paging.cachedIn
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.example.domain.interactor.GetSearchResultUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

class GalleryViewModel(
    getSearchResult: GetSearchResultUseCase
) : InstanceKeeper.Instance {

    private val viewModelScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())
    val state: Value<GalleryComponent.GalleryComponentState> = MutableValue(
        GalleryComponent.GalleryComponentState(
            getSearchResult(DEFAULT_QUERY).cachedIn(viewModelScope)
        )
    )

    override fun onDestroy() {
        viewModelScope.cancel()
    }

    private companion object {
        private const val DEFAULT_QUERY = "cats"
    }

}