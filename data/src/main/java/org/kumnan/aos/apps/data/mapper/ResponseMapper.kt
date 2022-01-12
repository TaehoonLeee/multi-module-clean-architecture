package org.kumnan.aos.apps.data.mapper

import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import org.kumnan.aos.apps.domain.model.status.Result

object ResponseMapper {

    fun responseToPhotoList(response: Result<UnsplashResponse>): Result<List<UnsplashPhoto>> {
        return when(response) {
            is Result.Success -> Result.Success(response.data.results, response.code)
            is Result.ApiError -> Result.ApiError(response.message, response.code)
            is Result.NetworkError -> Result.NetworkError(response.throwable)
            is Result.NullResult -> Result.NullResult()
            is Result.Loading -> Result.Loading()
        }
    }
}