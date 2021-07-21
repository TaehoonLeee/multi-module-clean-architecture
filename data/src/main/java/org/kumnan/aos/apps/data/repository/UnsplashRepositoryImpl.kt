package org.kumnan.aos.apps.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.kumnan.aos.apps.data.api.UnsplashService
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.repository.datasource.UnsplashPhotoPagingSource
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.domain.repository.UnsplashRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRepositoryImpl @Inject constructor(
    private val unsplashService: UnsplashService,
    private val responseMapToPhoto: (Result<UnsplashResponse>) -> Result<List<UnsplashPhoto>>
) : UnsplashRepository {
    override fun getSearchResultOfPage(query: String, page: Int) = flow {
        unsplashService.searchPhotos(query, page, 10).run {
            emit(responseMapToPhoto(this))
        }
    }

    override fun <T> getSearchResult(query: String): Flow<T> =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPhotoPagingSource(unsplashService, query) }
        ).flow as Flow<T>
}