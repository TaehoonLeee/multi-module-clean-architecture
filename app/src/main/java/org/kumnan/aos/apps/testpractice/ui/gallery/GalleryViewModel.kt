package org.kumnan.aos.apps.testpractice.ui.gallery

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import org.kumnan.aos.apps.domain.model.status.Result
import org.kumnan.aos.apps.domain.interactor.GetSearchResultOfPageUseCase
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import javax.inject.Inject

@HiltViewModel
class GalleryViewModel @Inject constructor(
    getSearchResultUseCase: GetSearchResultUseCase,
    getSearchResultOfPageUseCase: GetSearchResultOfPageUseCase
) : ViewModel() {

    val searchPageResult = getSearchResultOfPageUseCase.execute(DEFAULT_QUERY, FIRST_PAGE)
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)
        .filterIsInstance<Result.Success<List<UnsplashPhoto>>>()
        .map { it.data }
        .asLiveData()

    val searchResult = getSearchResultUseCase
        .execute<PagingData<UnsplashPhoto>>(DEFAULT_QUERY)
        .cachedIn(viewModelScope)
        .asLiveData()

    companion object {
        const val DEFAULT_QUERY = "cats"
        const val FIRST_PAGE = 1
    }
}