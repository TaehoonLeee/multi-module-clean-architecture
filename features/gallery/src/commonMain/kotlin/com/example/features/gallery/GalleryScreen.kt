package com.example.features.gallery

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.common.SimpleAsyncImage
import com.example.common.collectAsLazyPagingItems
import com.example.common.items

@Composable
fun GalleryScreen(galleryViewModel: GalleryViewModel) {
    val state = galleryViewModel.uiState.collectAsLazyPagingItems()

    LazyColumn {
        items(state) {
            SimpleAsyncImage(
                source = it?.urls?.regular?: "",
                modifier = Modifier.fillMaxSize().height(250.dp)
            )
        }
    }
}