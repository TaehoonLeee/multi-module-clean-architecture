package com.example.domain.repository

import com.example.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import com.example.domain.model.status.Result

interface UnsplashRepository {
    fun getSearchResultOfPage(query: String, page: Int): Flow<Result<List<UnsplashPhoto>>>

    // PagingData를 사용하기 위한 Generic Function
    fun <T> getSearchResult(query: String): Flow<T>
}