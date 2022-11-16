package com.example.data.network.ktor

import com.example.data.entity.ItemEntity
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import com.example.domain.model.status.Result
import javax.inject.Inject
import javax.inject.Named

class ItemService @Inject constructor(
    @Named("item") private val httpClient: HttpClient
) {

    suspend fun getItemList(): Result<List<ItemEntity>> {
        return try {
            val response = httpClient.get {  }

            if (response.status.isSuccess()) {
                Result.Success(response.body(), response.status.value)
            } else {
                Result.ApiError(response.status.description, response.status.value)
            }
        } catch (e: Exception) {
            Result.NetworkError(e)
        }
    }
}