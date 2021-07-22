package org.kumnan.aos.apps.testpractice.ui.gallery

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kumnan.aos.apps.data.api.UnsplashService
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.mapper.ResponseMapper
import org.kumnan.aos.apps.data.repository.UnsplashRepositoryImpl
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto.UnsplashPhotoUrls
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto.UnsplashUser
import org.kumnan.aos.apps.domain.entity.status.Result
import org.kumnan.aos.apps.domain.interactor.GetSearchResultOfPageUseCase
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import org.kumnan.aos.apps.testpractice.util.getOrAwaitValue

@RunWith(AndroidJUnit4::class)
class GalleryViewModelTest : TestCase() {

    private lateinit var viewModel: GalleryViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    public override fun setUp() {
        super.setUp()

        val fakePhotoList = listOf(
            UnsplashPhoto(
                id = "1",
                description = "first",
                urls = UnsplashPhotoUrls("","","","",""),
                user = UnsplashUser("first_user", "first_user")
            )
        )
        val fakeUnsplashService = object : UnsplashService {
            override suspend fun searchPhotos(
                query: String,
                page: Int,
                perPage: Int
            ): Result<UnsplashResponse> = Result.Success(UnsplashResponse(fakePhotoList, 1), 200)
        }
        val unSplashRepository = UnsplashRepositoryImpl(fakeUnsplashService, ResponseMapper::responseToPhotoList)

        val getSearchResultOfPageUseCase = GetSearchResultOfPageUseCase(
            unSplashRepository
        )
        val getSearchResultUseCase = GetSearchResultUseCase(
            unSplashRepository
        )

        viewModel = GalleryViewModel(
            getSearchResultUseCase, getSearchResultOfPageUseCase
        )
    }

    @Test
    fun testGalleryViewModel() {
        val result = viewModel.searchPageResult.getOrAwaitValue().firstOrNull {
            it.id == "1" && it.user.username == "first_user"
        }
        assert(result != null)
    }
}