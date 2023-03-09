package com.example.features.gallery

import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import com.example.common.ViewModel
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GalleryViewModel(
    getSearchResult: GetSearchResultUseCase
) : ViewModel(), KoinComponent {

    private val getSearchResult: GetSearchResultUseCase by inject()

    val uiState: Flow<PagingData<UnsplashPhoto>> = getSearchResult(DEFAULT_QUERY).cachedIn()

    private companion object {
        private const val DEFAULT_QUERY = "cats"
    }

}