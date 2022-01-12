package org.kumnan.aos.apps.data.network.ktor

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import org.kumnan.aos.apps.data.entity.ItemEntity
import org.kumnan.aos.apps.domain.model.status.Result
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class ItemService @Inject constructor(
    @Named("item") private val httpClient: HttpClient
) {

    suspend fun getItemList(): Result<List<ItemEntity>> {
        return try {
            val response = httpClient.get<HttpResponse>()

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