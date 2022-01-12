package org.kumnan.aos.apps.data.network.retrofit

import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.domain.model.status.Result
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface RetrofitUnsplashService : UnsplashService {

    @Headers("Accept-Version: v1", "Authorization: Client-ID $CLIENT_ID")
    @GET("search/photos")
    override suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Result<UnsplashResponse>

    companion object {
        private const val CLIENT_ID = "ti90oMOJyxTN-gKrvE39bi6LM2tbMAdOvey4QMKES0k"
    }
}