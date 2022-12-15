package com.example.common

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.*
import app.cash.paging.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

class LazyPagingItems<T: Any>(
	private val flow: Flow<PagingData<T>>
) {

	var itemSnapshotList by mutableStateOf(
		ItemSnapshotList<T>(0, 0, emptyList())
	)
		private set

	val itemCount: Int get() = itemSnapshotList.size

	private val differCallback: DifferCallback = object : DifferCallback {
		override fun onChanged(position: Int, count: Int) {
			if (count > 0) {
				updateItemSnapshotList()
			}
		}

		override fun onInserted(position: Int, count: Int) {
			if (count > 0) {
				updateItemSnapshotList()
			}
		}

		override fun onRemoved(position: Int, count: Int) {
			if (count > 0) {
				updateItemSnapshotList()
			}
		}
	}

	private val pagingDataDiffer = object : PagingDataDiffer<T>(
		differCallback = differCallback,
		mainDispatcher = Dispatchers.Main
	) {
		override suspend fun presentNewList(
			previousList: NullPaddedList<T>,
			newList: NullPaddedList<T>,
			lastAccessedIndex: Int,
			onListPresentable: () -> Unit
		): Int? {
			onListPresentable()
			updateItemSnapshotList()
			return null
		}
	}

	private fun updateItemSnapshotList() {
		itemSnapshotList = pagingDataDiffer.snapshot()
	}

	operator fun get(index: Int): T? {
		pagingDataDiffer[index]
		return itemSnapshotList[index]
	}

	var loadState: CombinedLoadStates by mutableStateOf(
		CombinedLoadStates(
			refresh = InitialLoadStates.refresh,
			prepend = InitialLoadStates.prepend,
			append = InitialLoadStates.append,
			source = InitialLoadStates
		)
	)
		private set

	suspend fun collectLoadState() {
		pagingDataDiffer.loadStateFlow.collect {
			loadState = it
		}
	}

	suspend fun collectPagingData() {
		flow.collectLatest {
			pagingDataDiffer.collectFrom(it)
		}
	}
}

private val IncompleteLoadState: LoadState = LoadStateNotLoading(false)
private val InitialLoadStates = LoadStates(
	LoadStateLoading,
	IncompleteLoadState,
	IncompleteLoadState
)

@Composable
fun <T: Any> Flow<PagingData<T>>.collectAsLazyPagingItems(): LazyPagingItems<T> {
	val lazyPagingItems = remember(this) { LazyPagingItems(this) }

	LaunchedEffect(lazyPagingItems) {
		lazyPagingItems.collectPagingData()
	}
	LaunchedEffect(lazyPagingItems) {
		lazyPagingItems.collectLoadState()
	}

	return lazyPagingItems
}

fun <T: Any> LazyListScope.items(
	items: LazyPagingItems<T>,
	itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
	items(items.itemCount) { index ->
		itemContent(items[index])
	}
}