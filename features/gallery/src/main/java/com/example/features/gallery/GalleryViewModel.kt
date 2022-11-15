package com.example.features.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import mvi.ViewModelStore
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	initialState: GalleryState,
	private val getSearchResult: GetSearchResultUseCase,
) : ViewModelStore<GalleryIntent, GalleryState, GalleryMessage>(initialState) {

	init {
		viewModelScope.launch {
			accept(GalleryIntent.FetchPhotos)
		}
	}

	override fun onIntent(intent: GalleryIntent) = when (intent) {
		is GalleryIntent.FetchPhotos -> getSearchResult(DEFAULT_QUERY)
			.cachedIn(viewModelScope)
			.onEach { dispatch(GalleryMessage.Fetched(it)) }
			.launchIn(viewModelScope)
			.let {}
	}

	override fun reduce(state: GalleryState, message: GalleryMessage) = when (message) {
		is GalleryMessage.Fetched -> state.copy(data = message.result)
	}

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}