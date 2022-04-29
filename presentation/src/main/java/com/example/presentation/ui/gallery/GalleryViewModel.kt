package com.example.presentation.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.presentation.ui.gallery.GalleryStore.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import mvi.Executor
import mvi.Store
import mvi.ViewModelStore.Companion.create
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

	private val store: GalleryStore = object : GalleryStore, Store<Intent, State, Message> by create(
		State(),
		{ onIntent(it) },
		::reduce
	) {}

	val state get() = store.state

	init {
		viewModelScope.launch {
			store.accept(Intent.FetchPhotos)
		}
	}

	private fun Executor<Intent, Message>.onIntent(intent: Intent) {
		when (intent) {
			is Intent.FetchPhotos -> {
				getSearchResultUseCase<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
					.cachedIn(viewModelScope)
					.onEach { dispatch(Message.Fetched(it)) }
					.launchIn(viewModelScope)
			}
		}
	}

	private fun reduce(state: State, message: Message) = when (message) {
		is Message.Fetched -> state.copy(data = message.result)
	}

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}