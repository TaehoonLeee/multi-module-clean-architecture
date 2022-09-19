package com.example.testpractice

import com.example.data.cache.ItemDao
import com.example.data.entity.UnsplashResponse
import com.example.data.module.RepositoryModule
import com.example.data.network.UnsplashService
import com.example.data.network.ktor.ItemService
import com.example.data.repository.ItemRepositoryImpl
import com.example.data.repository.UnsplashRepositoryImpl
import com.example.domain.model.UnsplashPhoto
import com.example.domain.model.status.Result
import com.example.domain.repository.ItemRepository
import com.example.domain.repository.UnsplashRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
	components = [SingletonComponent::class],
	replaces = [RepositoryModule::class]
)
object FakeModule {

	@Provides
	fun provideUnsplashService(): UnsplashService = object : UnsplashService {
		override suspend fun searchPhotos(query: String, page: Int, perPage: Int): Result<UnsplashResponse> {
			val photoList = listOf(
				UnsplashPhoto(
					id = "1",
					description = "first",
					urls = UnsplashPhoto.UnsplashPhotoUrls("", "", "", "", ""),
					user = UnsplashPhoto.UnsplashUser("first_user", "first_user")
				)
			)
			val response = UnsplashResponse(
				photoList, 1
			)

			return Result.Success(response, 200)
		}
	}

	@Singleton
	@Provides
	fun provideUnsplashRepository(
		unsplashService: UnsplashService
	): UnsplashRepository {
		return UnsplashRepositoryImpl(unsplashService)
	}

	@Singleton
	@Provides
	fun provideItemRepository(
		itemService: ItemService,
		itemDao: ItemDao
	): ItemRepository = ItemRepositoryImpl(itemService, itemDao)

}