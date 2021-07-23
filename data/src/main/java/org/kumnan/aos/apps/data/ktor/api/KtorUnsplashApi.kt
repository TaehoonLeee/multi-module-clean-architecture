package org.kumnan.aos.apps.data.ktor.api

import io.ktor.client.call.*
import io.ktor.client.statement.*
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.ktor.KtorNetworkService
import org.kumnan.aos.apps.domain.entity.status.Result
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
    ): Result<UnsplashResponse> {
        return try {
            val response = ktorNetworkService.get<HttpResponse>(
                "https://api.unsplash.com/search/photos?client_id=$CLIENT_ID&query=$query&page=$page&per_page=$pageSize"
            )
            Result.Success(response.receive(), response.status.value)
        } catch (e: Exception) {
            Result.NetworkError(e)
        }
    }

    companion object {
        private const val CLIENT_ID = "ti90oMOJyxTN-gKrvE39bi6LM2tbMAdOvey4QMKES0k"
    }
}