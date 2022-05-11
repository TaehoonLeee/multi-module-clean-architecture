package com.example.domain.interactor

import com.example.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetSearchResultUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {

    operator fun <T> invoke(query: String) = unsplashRepository.getSearchResult<T>(query)
}