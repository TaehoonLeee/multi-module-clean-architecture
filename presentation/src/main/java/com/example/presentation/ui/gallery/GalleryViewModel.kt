package com.example.presentation.ui.gallery

import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mvi.Executor
import mvi.ViewModelStore
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	override val initialState: GalleryState,
	private val getSearchResultUseCase: GetSearchResultUseCase,
) : ViewModelStore<GalleryIntent, GalleryState, GalleryMessage>() {

	init {
		viewModelScope.launch {
			accept(GalleryIntent.FetchPhotos)
		}
	}

	override fun Executor<GalleryIntent, GalleryMessage>.onIntent(intent: GalleryIntent) {
		when (intent) {
			is GalleryIntent.FetchPhotos -> getSearchResultUseCase<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
				.cachedIn(viewModelScope)
				.onEach { dispatch(GalleryMessage.Fetched(it)) }
				.launchIn(viewModelScope)
		}
	}

	override fun reduce(state: GalleryState, message: GalleryMessage) = when (message) {
		is GalleryMessage.Fetched -> state.copy(data = message.result)
	}

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}