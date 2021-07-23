package org.kumnan.aos.apps.data.repository.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.kumnan.aos.apps.data.ktor.api.KtorUnsplashApi
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto

class KtorUnsplashPagingSource constructor(
    private val ktorUnsplashApi: KtorUnsplashApi,
    private val query: String
) : PagingSource<Int, UnsplashPhoto>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashPhoto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        val position = params.key?: 1
        val response = ktorUnsplashApi.getSearchResponse(query, position, params.loadSize)

        return try {
            LoadResult.Page(
                data = response.results,
                prevKey = if (position == 1) null else position - 1,
                nextKey = if (position == response.totalPages) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}