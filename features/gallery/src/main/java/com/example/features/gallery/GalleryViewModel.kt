package com.example.features.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import com.example.mvi.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
	initialState: GalleryState,
	getSearchResult: GetSearchResultUseCase,
) : ViewModel() {

	private val stateProducer = actionStateProducer<GalleryAction, GalleryState>(
		initialState = initialState,
		mutationFlows = listOf(
			getSearchResult.mutation(DEFAULT_QUERY)
		),
		actionTransform = {
			onAction<GalleryAction.FetchPhotos> {
				flow.flatMapLatest {
					getSearchResult.mutation(it.query)
				}
			}
		}
	)

	val uiState = stateProducer.state
	val process = stateProducer.process

	private fun GetSearchResultUseCase.mutation(
		query: String
	): Flow<Mutation<GalleryState>> = invoke(query)
		.cachedIn(viewModelScope)
		.map { Async.Success(it) }
		.onStart<Async<PagingData<UnsplashPhoto>>> { Async.Loading }
		.map { async ->
			when (async) {
				is Async.Success -> mutation { copy(data = async.data) }
				is Async.Loading -> Mutations.identity()
			}
		}

	companion object {
		const val DEFAULT_QUERY = "cats"
	}
}