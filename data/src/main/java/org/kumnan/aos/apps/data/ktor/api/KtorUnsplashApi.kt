package org.kumnan.aos.apps.data.ktor.api

import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.ktor.KtorNetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorUnsplashApi @Inject constructor(
    private val ktorNetworkService: KtorNetworkService
) {

    suspend fun getSearchResponse(
        query: String,
        page: Int = 1,
        pageSize: Int = 20
    ): UnsplashResponse {
        return ktorNetworkService.get(
            "https://api.unsplash.com/search/photos?client_id=$CLIENT_ID&query=$query&page=$page&per_page=$pageSize"
        )
    }

    companion object {
        private const val CLIENT_ID = "ti90oMOJyxTN-gKrvE39bi6LM2tbMAdOvey4QMKES0k"
    }
}