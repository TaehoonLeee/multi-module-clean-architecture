package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetSearchResultOfPageUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    fun execute(query: String, page: Int) = unsplashRepository.getSearchResultOfPage(query, page)
}