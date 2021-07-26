package org.kumnan.aos.apps.data.network.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
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
            val response = httpClient.get<HttpResponse>(path = "search/photos") {
                parameter("query", query)
                parameter("page", page)
                parameter("per_page", perPage)
            }

            if (response.status.isSuccess()) {
                Result.Success(response.receive(), response.status.value)
            } else {
                Result.ApiError(response.status.description, response.status.value)
            }
        } catch (e: Exception) {
            Result.NetworkError(e)
        }
    }
}