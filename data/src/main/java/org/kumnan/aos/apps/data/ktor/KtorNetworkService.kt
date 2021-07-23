package org.kumnan.aos.apps.data.ktor

import io.ktor.client.*
import io.ktor.client.request.*
import javax.inject.Inject

class KtorNetworkService @Inject constructor(val httpClient: HttpClient) {

    suspend inline fun <reified T> get(url: String): T = httpClient.get(url)

    suspend inline fun <reified T> post(
        url: String,
        block: HttpRequestBuilder.() -> Unit = { }
    ): T = httpClient.post(url, block)
}