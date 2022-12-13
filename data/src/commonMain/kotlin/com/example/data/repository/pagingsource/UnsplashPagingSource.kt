package com.example.data.repository.pagingsource

import app.cash.paging.*
import com.example.data.network.UnsplashApiExecutor
import com.example.domain.model.UnsplashPhoto
import com.example.domain.model.status.Result

internal class UnsplashPagingSource(
    private val query: String,
    private val unsplashApiExecutor: UnsplashApiExecutor
) : PagingSource<Int, UnsplashPhoto>() {

    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: PagingSourceLoadParams<Int>): PagingSourceLoadResult<Int, UnsplashPhoto> {
        val position = params.key?: 1
        val response = unsplashApiExecutor.searchPhotos(query, position, params.loadSize)

        return if (response is Result.Success) {
            PagingSourceLoadResultPage(
                data = response.data.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.data.totalPages) null else position + 1
            ) as PagingSourceLoadResult<Int, UnsplashPhoto>
        } else {
            PagingSourceLoadResultError<Int, UnsplashPhoto>(Exception()) as PagingSourceLoadResult<Int, UnsplashPhoto>
        }
    }
}