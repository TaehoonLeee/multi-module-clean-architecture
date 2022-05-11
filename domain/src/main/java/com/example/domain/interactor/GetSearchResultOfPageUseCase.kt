package com.example.domain.interactor

import com.example.domain.repository.UnsplashRepository
import javax.inject.Inject

class GetSearchResultOfPageUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    fun execute(query: String, page: Int) = unsplashRepository.getSearchResultOfPage(query, page)
}