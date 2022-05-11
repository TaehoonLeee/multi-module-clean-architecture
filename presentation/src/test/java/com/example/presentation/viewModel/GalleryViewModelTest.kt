package com.example.presentation.viewModel

import androidx.paging.*
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import com.example.domain.repository.UnsplashRepository
import com.example.presentation.ui.gallery.GalleryViewModel
import com.example.presentation.util.TestCoroutinesRule
import com.example.presentation.util.fakes.FakePhotoListHolder
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GalleryViewModelTest : TestCase() {

	@get:Rule(order = 0)
	val coroutineRule = TestCoroutinesRule()

	private val mockUnsplashRepository = mock(UnsplashRepository::class.java)

	@Test
	@ExperimentalCoroutinesApi
	fun `뷰모델 리스트 초기화`() = coroutineRule.runTest {
		val viewModel = createViewModel()

		assertEquals(
			viewModel.searchResult.drop(1).first().parseData(),
			PagingData.from(FakePhotoListHolder.fakePhotoList).parseData()
		)
	}

	private fun createViewModel() = GalleryViewModel(
		GetSearchResultUseCase(mockUnsplashRepository)
	).also {
		`when`(mockUnsplashRepository.getSearchResult<PagingData<UnsplashPhoto>>(ArgumentMatchers.anyString()))
			.thenReturn(flowOf(PagingData.from(FakePhotoListHolder.fakePhotoList)))
	}

	private suspend fun PagingData<UnsplashPhoto>.parseData(): List<UnsplashPhoto> = buildList {
		val emptyCallback = object : DifferCallback {
			override fun onChanged(position: Int, count: Int) = Unit
			override fun onInserted(position: Int, count: Int) = Unit
			override fun onRemoved(position: Int, count: Int) = Unit
		}
		object : PagingDataDiffer<UnsplashPhoto>(emptyCallback, coroutineRule.testDispatcher) {
			override suspend fun presentNewList(
				previousList: NullPaddedList<UnsplashPhoto>,
				newList: NullPaddedList<UnsplashPhoto>,
				newCombinedLoadStates: CombinedLoadStates,
				lastAccessedIndex: Int,
				onListPresentable: () -> Unit
			): Int? {
				for (idx in 0 until newList.size) {
					add(newList.getFromStorage(idx))
				}
				onListPresentable()
				return null
			}
		}.collectFrom(this@parseData)
	}
}