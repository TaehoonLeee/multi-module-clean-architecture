package com.example.presentation.ui.gallery

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import mvi.*
import mvi.DefaultStore.Companion.create
import com.example.presentation.ui.gallery.GalleryStore.Message
import com.example.presentation.ui.gallery.GalleryStore.Intent
import com.example.presentation.ui.gallery.GalleryStore.State
import kotlinx.coroutines.launch
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

    private val store: GalleryStore = object : GalleryStore, Store<Intent, State, Message> by create(
        State(),
        viewModelScope,
        ExecutorImpl(),
        ReducerImpl()
    ) {}

    val state = store.state

    init {
        viewModelScope.launch {
            store.accept(Intent.FetchPhotos)
        }
    }

    private inner class ExecutorImpl : DefaultExecutor<Intent, Message>() {

        override fun executeIntent(intent: Intent) {
            when (intent) {
                is Intent.FetchPhotos -> {
                    getSearchResultUseCase<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
                        .cachedIn(viewModelScope)
                        .onEach { dispatch(Message.Fetched(it)) }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    private inner class ReducerImpl : Reducer<State, Message> {
        override fun reduce(
            state: State,
            message: Message
        ): State = when (message) {
            is Message.Fetched -> state.copy(data = message.result)
        }
    }

    companion object {
        const val DEFAULT_QUERY = "cats"
    }
}