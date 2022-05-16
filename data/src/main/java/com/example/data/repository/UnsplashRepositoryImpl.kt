package com.example.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.data.mapper.ResponseMapper
import com.example.data.network.UnsplashService
import com.example.data.repository.datasource.UnsplashPhotoPagingSource
import com.example.domain.repository.UnsplashRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashService: UnsplashService
) : UnsplashRepository {
    override fun getSearchResultOfPage(query: String, page: Int) = flow {
        unsplashService.searchPhotos(query, page, 10).run {
            emit(ResponseMapper.responseToPhotoList(this))
        }
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