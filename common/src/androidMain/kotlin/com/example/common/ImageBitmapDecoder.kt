package com.example.common

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap

actual fun ByteArray.decode(): ImageBitmap {
	return BitmapFactory.decodeByteArray(this, 0, size).asImageBitmap()
}