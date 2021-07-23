package org.kumnan.aos.apps.data.network.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.client.request.get
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.domain.entity.status.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KtorUnsplashService @Inject constructor(
    private val httpClient: HttpClient
) : UnsplashService {

    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Result<UnsplashResponse> {
        return try {
            val response = httpClient.get<HttpResponse>(
                "https://api.unsplash.com/search/photos?client_id=$CLIENT_ID&query=$query&page=$page&per_page=$perPage"
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