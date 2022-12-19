package com.example.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.cache.disk.DiskCacheBuilder
import com.seiko.imageloader.cache.memory.MemoryCacheBuilder
import okio.Path.Companion.toOkioPath

@Composable
actual fun createImageLoader(): ImageLoader {
	val context = LocalContext.current

	return if (imageLoader != null) imageLoader!! else {
		ImageLoaderBuilder(context)
			.memoryCache {
				MemoryCacheBuilder(context)
					.maxSizePercent(.25)
					.build()
			}
			.diskCache {
				DiskCacheBuilder()
					.directory(context.cacheDir.resolve("image_cache").toOkioPath())
					.maxSizeBytes(512L * 1024 * 1024)
					.build()
			}
			.build()
			.also(::imageLoader::set)
	}
}