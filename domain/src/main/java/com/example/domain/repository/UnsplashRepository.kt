package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import com.example.domain.model.status.Result

interface UnsplashRepository {
    fun getSearchResultOfPage(query: String, page: Int): Flow<Result<List<UnsplashPhoto>>>

    fun getSearchResult(query: String): Flow<PagingData<UnsplashPhoto>>
}