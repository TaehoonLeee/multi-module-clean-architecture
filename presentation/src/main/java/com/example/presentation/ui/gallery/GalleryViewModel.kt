package com.example.presentation.ui.gallery

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mvi.Executor
import mvi.ViewModelStore
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	override val initialState: State,
	private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModelStore<Intent, State, Message>() {

	init {
		viewModelScope.launch {
			accept(Intent.FetchPhotos)
		}
	}

	override fun Executor<Intent, Message>.onIntent(intent: Intent) {
		when (intent) {
			is Intent.FetchPhotos -> {
				getSearchResultUseCase<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
					.cachedIn(viewModelScope)
					.onEach { dispatch(Message.Fetched(it)) }
					.launchIn(viewModelScope)
			}
		}
	}

	override fun reduce(state: State, message: Message) = when (message) {
		is Message.Fetched -> state.copy(data = message.result)
	}

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}