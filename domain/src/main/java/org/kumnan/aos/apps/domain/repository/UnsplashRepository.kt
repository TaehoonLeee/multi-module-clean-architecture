package org.kumnan.aos.apps.domain.repository

import kotlinx.coroutines.flow.Flow
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.status.Result

interface UnsplashRepository {
    fun getSearchResultOfPage(query: String, page: Int): Flow<Result<List<UnsplashPhoto>>>

    fun <T> getSearchResult(query: String): Flow<T>
}