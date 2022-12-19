package com.example.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.LocalImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter

internal var imageLoader: ImageLoader? = null

@Composable
expect fun createImageLoader(): ImageLoader

@Composable
fun SimpleAsyncImage(
	source: String,
	modifier: Modifier = Modifier
) {
	CompositionLocalProvider(LocalImageLoader provides createImageLoader()) {
		Image(
			modifier = modifier,
			contentDescription = null,
			contentScale = ContentScale.FillWidth,
			painter = rememberAsyncImagePainter(source)
		)
	}
}