package com.example.features.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	getSearchResult: GetSearchResultUseCase,
) : ViewModel() {

	val searchResult = getSearchResult(DEFAULT_QUERY)
		.cachedIn(viewModelScope)

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}