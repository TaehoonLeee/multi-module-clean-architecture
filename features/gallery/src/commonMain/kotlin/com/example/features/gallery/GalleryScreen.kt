package com.example.features.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.example.common.SimpleAsyncImage
import com.example.common.collectAsLazyPagingItems
import com.example.common.items

@Composable
fun GalleryScreen(galleryComponent: GalleryComponent) {
    val state by galleryComponent.state.subscribeAsState()
    val pagingItems = state.searchResult.collectAsLazyPagingItems()

    LazyColumn {
        items(pagingItems) {
            SimpleAsyncImage(
                source = it?.urls?.regular?: "",
                modifier = Modifier.fillMaxSize().height(250.dp)
            )
        }
    }
}