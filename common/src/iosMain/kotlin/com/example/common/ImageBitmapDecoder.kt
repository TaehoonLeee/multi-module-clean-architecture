package com.example.common

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import org.jetbrains.skia.Image

actual fun ByteArray.decode(): ImageBitmap {
	return Image.makeFromEncoded(this).toComposeImageBitmap()
}