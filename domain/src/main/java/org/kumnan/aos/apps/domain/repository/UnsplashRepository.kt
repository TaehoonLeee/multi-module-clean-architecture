package org.kumnan.aos.apps.domain.repository

import kotlinx.coroutines.flow.Flow
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import org.kumnan.aos.apps.domain.model.status.Result

interface UnsplashRepository {
    fun getSearchResultOfPage(query: String, page: Int): Flow<Result<List<UnsplashPhoto>>>

    // PagingData를 사용하기 위한 Generic Function
    fun <T> getSearchResult(query: String): Flow<T>
}