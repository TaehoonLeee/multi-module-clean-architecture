package com.example.common

import androidx.compose.runtime.Composable
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.ImageLoaderBuilder
import com.seiko.imageloader.cache.disk.DiskCacheBuilder
import com.seiko.imageloader.cache.memory.MemoryCacheBuilder
import okio.Path.Companion.toPath
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@Composable
actual fun createImageLoader(): ImageLoader {
	return if (imageLoader != null) imageLoader!! else {
		ImageLoaderBuilder()
			.memoryCache {
				MemoryCacheBuilder()
					.maxSizePercent(.25)
					.build()
			}
			.diskCache {
				val cacheDir = NSFileManager.defaultManager
					.URLForDirectory(NSCachesDirectory, NSUserDomainMask, null, true, null)
					?.path
					.orEmpty()

				DiskCacheBuilder()
					.directory(cacheDir.toPath().resolve("image_cache"))
					.maxSizeBytes(512L * 1024 * 1024)
					.build()
			}
			.build()
			.also(::imageLoader::set)
	}
}