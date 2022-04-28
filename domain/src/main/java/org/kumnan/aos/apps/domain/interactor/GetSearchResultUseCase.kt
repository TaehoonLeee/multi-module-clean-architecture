package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {

    operator fun <T> invoke(query: String) = unsplashRepository.getSearchResult<T>(query)
}