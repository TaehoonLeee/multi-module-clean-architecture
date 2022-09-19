package com.example.presentation.util.extensions

import androidx.paging.*

private val emptyCallback = object : DifferCallback {
	override fun onChanged(position: Int, count: Int) = Unit
	override fun onInserted(position: Int, count: Int) = Unit
	override fun onRemoved(position: Int, count: Int) = Unit
}

suspend fun <T: Any> PagingData<T>.parseData(): List<T> = buildList {
	object : PagingDataDiffer<T>(emptyCallback) {
		override suspend fun presentNewList(
			previousList: NullPaddedList<T>,
			newList: NullPaddedList<T>,
			lastAccessedIndex: Int,
			onListPresentable: () -> Unit
		): Int? {
			onListPresentable()
			for (idx in 0 until newList.size) {
				add(newList.getFromStorage(idx))
			}

			return null
		}
	}.collectFrom(this@parseData)
}