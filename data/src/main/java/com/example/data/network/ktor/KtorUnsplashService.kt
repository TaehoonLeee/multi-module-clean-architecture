package com.example.data.network.ktor

import com.example.data.entity.UnsplashResponse
import com.example.data.network.UnsplashService
import com.example.domain.model.status.Result
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Named

class KtorUnsplashService @Inject constructor(
    @Named("unsplash") private val httpClient: HttpClient
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