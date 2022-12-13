package com.example.data.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.example.data.network.UnsplashApiExecutor
import com.example.data.repository.pagingsource.UnsplashPagingSource
import com.example.domain.model.UnsplashPhoto
import com.example.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.Flow

class UnsplashRepositoryImpl(
    private val unsplashApiExecutor: UnsplashApiExecutor
) : UnsplashRepository {

    override fun getSearchResult(query: String): Flow<PagingData<UnsplashPhoto>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(query, unsplashApiExecutor) }
        ).flow
    }
}