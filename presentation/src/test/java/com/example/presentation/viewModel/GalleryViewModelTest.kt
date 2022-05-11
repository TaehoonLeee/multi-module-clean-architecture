package com.example.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.*
import com.example.domain.interactor.GetSearchResultUseCase
import com.example.domain.model.UnsplashPhoto
import com.example.domain.repository.UnsplashRepository
import com.example.presentation.ui.gallery.GalleryState
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
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class GalleryViewModelTest : TestCase() {

	@get:Rule(order = 0)
	val coroutineRule = TestCoroutinesRule()

	private val mockUnsplashRepository = Mockito.mock(UnsplashRepository::class.java)

	@Test
	@ExperimentalCoroutinesApi
	fun `뷰모델 리스트 초기화`() = coroutineRule.runTest {
		val viewModel = createViewModel()

		assertEquals(
			viewModel.state.drop(1).first().data.parseData(),
			PagingData.from(FakePhotoListHolder.fakePhotoList).parseData()
		)
	}

	private fun createViewModel() = GalleryViewModel(
		GalleryState(PagingData.empty()),
		GetSearchResultUseCase(mockUnsplashRepository)
	).apply {
		Mockito.`when`(mockUnsplashRepository.getSearchResult<PagingData<UnsplashPhoto>>(ArgumentMatchers.anyString()))
			.thenReturn(flowOf(PagingData.from(FakePhotoListHolder.fakePhotoList)))
	}

	private suspend fun PagingData<UnsplashPhoto>.parseData(): List<UnsplashPhoto> = buildList {
		object : PagingDataDiffer<UnsplashPhoto>(
			object : DifferCallback {
				override fun onChanged(position: Int, count: Int) = Unit
				override fun onInserted(position: Int, count: Int) = Unit
				override fun onRemoved(position: Int, count: Int) = Unit
			},
			coroutineRule.testDispatcher
		) {
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