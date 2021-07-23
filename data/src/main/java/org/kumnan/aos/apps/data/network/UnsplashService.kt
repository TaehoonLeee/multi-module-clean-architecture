package org.kumnan.aos.apps.data.network

import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.domain.entity.status.Result

interface UnsplashService {

    suspend fun searchPhotos(query: String, page: Int = 1, perPage: Int = 20): Result<UnsplashResponse>
}