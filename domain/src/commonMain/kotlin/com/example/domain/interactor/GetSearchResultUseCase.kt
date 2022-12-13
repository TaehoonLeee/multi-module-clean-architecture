package com.example.domain.interactor

import com.example.domain.repository.UnsplashRepository

class GetSearchResultUseCase(
    private val unsplashRepository: UnsplashRepository
) {

    operator fun invoke(query: String) = unsplashRepository.getSearchResult(query)
}