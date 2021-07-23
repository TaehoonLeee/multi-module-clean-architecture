package org.kumnan.aos.apps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kumnan.aos.apps.data.ktor.api.KtorUnsplashApi
import org.kumnan.aos.apps.data.repository.datasource.KtorUnsplashPagingSource
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorUnsplashRepositoryImpl @Inject constructor(
    private val ktorUnsplashApi: KtorUnsplashApi
) : UnsplashRepository {

    override fun getSearchResultOfPage(
        query: String,
        page: Int
    ): Flow<Result<List<UnsplashPhoto>>> = flow {
        val response = ktorUnsplashApi.getSearchResponse(query, page)
        emit(Result.Success(response.results, 200))
    }

    override fun <T> getSearchResult(query: String): Flow<T> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { KtorUnsplashPagingSource(ktorUnsplashApi, query) }
        ).flow as Flow<T>
}