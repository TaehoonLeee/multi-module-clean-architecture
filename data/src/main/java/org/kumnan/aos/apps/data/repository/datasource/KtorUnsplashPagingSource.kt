package org.kumnan.aos.apps.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.status.Result

class KtorUnsplashPagingSource constructor(
    private val unsplashService: UnsplashService,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key?: 1
        val response = unsplashService.searchPhotos(query, position, params.loadSize)

        return if (response is Result.Success) {
            LoadResult.Page(
                data = response.data.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.data.totalPages) null else position + 1
            )
        } else {
            LoadResult.Error(Exception())
        }
    }
}