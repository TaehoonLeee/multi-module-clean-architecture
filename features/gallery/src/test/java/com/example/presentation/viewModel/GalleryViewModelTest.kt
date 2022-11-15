package com.example.presentation.viewModel

import androidx.paging.PagingData
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.repository.UnsplashRepository
import com.example.features.gallery.GalleryViewModel
import com.example.presentation.util.TestCoroutinesRule
import com.example.presentation.util.extensions.parseData
import com.example.presentation.util.fakes.FakePhotoListHolder
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GalleryViewModelTest : TestCase() {

	@get:Rule(order = 0)
	val coroutineRule = TestCoroutinesRule()

	private val mockUnsplashRepository = mock(UnsplashRepository::class.java)

	@Before
	fun repositoryInit() {
		`when`(mockUnsplashRepository.getSearchResult(ArgumentMatchers.anyString()))
			.thenReturn(flowOf(PagingData.from(FakePhotoListHolder.fakePhotoList)))
	}

	@Test
	@ExperimentalCoroutinesApi
	fun `뷰모델 리스트 초기화`() = coroutineRule.runTest {
		val viewModel = createViewModel()

		assertEquals(
			viewModel.searchResult.first().parseData(),
			PagingData.from(FakePhotoListHolder.fakePhotoList).parseData()
		)
	}

	private fun createViewModel() = GalleryViewModel(GetSearchResultUseCase(mockUnsplashRepository))
}