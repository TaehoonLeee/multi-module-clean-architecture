package org.kumnan.aos.apps.testpractice.util.fakes

import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.testpractice.util.fakes.data.FakePhotoListHolder

class FakeUnsplashService : UnsplashService {
    override suspend fun searchPhotos(
        query: String,
        page: Int,
        perPage: Int
    ): Result<UnsplashResponse> {
        val fakeUnsplashResponse = UnsplashResponse(
            FakePhotoListHolder.fakePhotoList, 1
        )
        return Result.Success(fakeUnsplashResponse, 200)
    }
}