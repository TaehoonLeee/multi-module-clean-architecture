package com.example.domain.repository

import app.cash.paging.PagingData
import com.example.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow

interface UnsplashRepository {
    fun getSearchResult(query: String): Flow<PagingData<UnsplashPhoto>>
}