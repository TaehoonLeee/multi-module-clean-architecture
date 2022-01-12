package org.kumnan.aos.apps.testpractice.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingSource
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.kumnan.aos.apps.data.entity.UnsplashResponse
import org.kumnan.aos.apps.data.mapper.ResponseMapper
import org.kumnan.aos.apps.data.network.UnsplashService
import org.kumnan.aos.apps.data.repository.UnsplashRepositoryImpl
import org.kumnan.aos.apps.data.repository.datasource.UnsplashPhotoPagingSource
import org.kumnan.aos.apps.domain.model.status.Result
import org.kumnan.aos.apps.domain.interactor.GetSearchResultOfPageUseCase
import org.kumnan.aos.apps.domain.interactor.GetSearchResultUseCase
import org.kumnan.aos.apps.testpractice.ui.gallery.GalleryViewModel
import org.kumnan.aos.apps.testpractice.util.TestCoroutineRule
import org.kumnan.aos.apps.testpractice.util.fakes.FakePhotoListHolder
import org.kumnan.aos.apps.testpractice.util.getOrAwaitValue
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GalleryViewModelTest : TestCase() {

    @get:Rule(order = 0)
    val coroutineRule = TestCoroutineRule()

    @get:Rule(order = 1)
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockUnsplashService = mock(UnsplashService::class.java)
    private val fakeUnsplashRepository = UnsplashRepositoryImpl(
        mockUnsplashService, ResponseMapper::responseToPhotoList
    )

    @Test
    @ExperimentalCoroutinesApi
    fun `뷰모델 리스트 초기화`() = coroutineRule.testCoroutineDispatcher.runBlockingTest {
        val viewModel = createViewModel()

        val pageResult = viewModel.searchPageResult.getOrAwaitValue()
        assertEquals(pageResult, FakePhotoListHolder.fakePhotoList)

        val pagingSource = UnsplashPhotoPagingSource(mockUnsplashService, "")
        assertEquals(
            PagingSource.LoadResult.Page(
                data = FakePhotoListHolder.fakePhotoList,
                prevKey = null,
                nextKey = null
            ),
            pagingSource.load(
                PagingSource.LoadParams.Refresh(
                    key = null,
                    loadSize = 2,
                    placeholdersEnabled = false
                )
            )
        )
    }

    private suspend fun createViewModel() = GalleryViewModel(
        GetSearchResultUseCase(fakeUnsplashRepository),
        GetSearchResultOfPageUseCase(fakeUnsplashRepository)
    ).apply {
        val fakeUnsplashResponse = UnsplashResponse(FakePhotoListHolder.fakePhotoList, 1)

        `when`(mockUnsplashService.searchPhotos(anyString(), anyInt(), anyInt()))
            .thenReturn(Result.Success(fakeUnsplashResponse, 200))
    }
}