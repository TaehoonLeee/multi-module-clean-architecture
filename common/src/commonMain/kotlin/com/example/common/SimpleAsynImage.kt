package com.example.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

private val imageFetcher = HttpClient()
expect fun ByteArray.decode(): ImageBitmap

@Composable
fun SimpleAsyncImage(
	source: String,
	modifier: Modifier = Modifier
) {
	val image by produceState<ImageBitmap?>(null) {
		val image = imageFetcher.get(source).body<ByteArray>()
		value = image.decode()
	}

	image?.let {
		Image(
			bitmap = it,
			modifier = modifier,
			contentDescription = null,
			contentScale = ContentScale.FillWidth
		)
	}
}