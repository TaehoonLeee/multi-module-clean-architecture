package com.example.domain.interactor

import com.example.domain.repository.UnsplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSearchResultUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {

    operator fun invoke(query: String) = unsplashRepository.getSearchResult(query)
}