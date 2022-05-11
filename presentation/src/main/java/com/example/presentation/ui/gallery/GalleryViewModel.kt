package com.example.presentation.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
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
class GalleryViewModel @Inject constructor(
	getSearchResult: GetSearchResultUseCase,
) : ViewModel() {

	val searchResult = getSearchResult<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
		.cachedIn(viewModelScope)
		.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), PagingData.empty())

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}