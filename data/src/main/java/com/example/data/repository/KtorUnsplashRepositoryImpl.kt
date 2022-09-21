package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.mapper.ResponseMapper
import com.example.data.network.UnsplashService
import com.example.data.repository.datasource.UnsplashPhotoPagingSource
import com.example.domain.model.UnsplashPhoto
import com.example.domain.repository.UnsplashRepository
import com.example.domain.model.status.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class KtorUnsplashRepositoryImpl @Inject constructor(
    private val unsplashService: UnsplashService
) : UnsplashRepository {

    override fun getSearchResultOfPage(
        query: String,
        page: Int
    ): Flow<Result<List<UnsplashPhoto>>> = flow {
        val response = unsplashService.searchPhotos(query, page)
        emit(ResponseMapper.responseToPhotoList(response))
    }

    override fun getSearchResult(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPhotoPagingSource(unsplashService, query) }
        ).flow
}