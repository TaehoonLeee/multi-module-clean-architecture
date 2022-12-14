package com.example.features.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.*
import app.cash.paging.*
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun GalleryScreen(galleryComponent: GalleryComponent) {
    val state by galleryComponent.state.subscribeAsState()
    val pagingItems = state.searchResult.collectAsLazyPagingItems()

    LazyColumn {
        items(pagingItems) {

        }
    }
}

class LazyPagingItems<T: Any>(
    private val flow: Flow<PagingData<T>>
) {

    val itemCount: Int get() = pagingDataDiffer.size
    val recomposerPlaceholder: MutableState<Int> = mutableStateOf(0)

    private val differCallback = object : DifferCallback {
        override fun onChanged(position: Int, count: Int) {
            recomposerPlaceholder.value++
        }

        override fun onInserted(position: Int, count: Int) {
            recomposerPlaceholder.value++
        }

        override fun onRemoved(position: Int, count: Int) {
            recomposerPlaceholder.value++
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
            recomposerPlaceholder.value++

            return null
        }
    }

    operator fun get(index: Int): T? {
        return pagingDataDiffer[index]
    }

    fun peek(index: Int): T? {
        return pagingDataDiffer.peek(index)
    }

    fun snapshot(): ItemSnapshotList<T> {
        return pagingDataDiffer.snapshot()
    }

    fun retry() {
        pagingDataDiffer.retry()
    }

    fun refresh() {
        pagingDataDiffer.refresh()
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
    IncompleteLoadState,
    IncompleteLoadState,
    IncompleteLoadState
)

@Composable
fun <T: Any> Flow<PagingData<T>>.collectAsLazyPagingItems(): LazyPagingItems<T> {
    val lazyPagingItems = remember(this) { LazyPagingItems(this) }

    LaunchedEffect(lazyPagingItems) {
        launch { lazyPagingItems.collectPagingData() }
        launch { lazyPagingItems.collectLoadState() }
    }

    return lazyPagingItems
}

fun <T: Any> LazyListScope.items(
    lazyPagingItems: LazyPagingItems<T>,
    itemContent: @Composable LazyItemScope.(value: T?) -> Unit
) {
    val recomposerPlaceholder = lazyPagingItems.recomposerPlaceholder.value

    items(lazyPagingItems.itemCount) { index ->
        val item = lazyPagingItems[index]
        itemContent(item)
    }
}