package com.example.features.gallery

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.example.common.collectAsLazyPagingItems
import com.example.common.items

@Composable
fun GalleryScreen(galleryComponent: GalleryComponent) {
    val state by galleryComponent.state.subscribeAsState()
    val pagingItems = state.searchResult.collectAsLazyPagingItems()

    LazyColumn {
        items(pagingItems) {

        }
    }
}