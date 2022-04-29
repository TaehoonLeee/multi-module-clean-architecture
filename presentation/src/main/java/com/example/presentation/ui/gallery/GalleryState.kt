package com.example.presentation.ui.gallery

import androidx.paging.PagingData
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.kumnan.aos.apps.domain.model.UnsplashPhoto
import javax.inject.Inject

data class State @Inject constructor(
	val data: PagingData<UnsplashPhoto>
) {
	@Module
	@InstallIn(ActivityRetainedComponent::class)
	object Injection {

		@Provides
		fun provideData() = PagingData.empty<UnsplashPhoto>()
	}
}

sealed interface Intent {
	object FetchPhotos : Intent
}

sealed interface Message {
	class Fetched(val result: PagingData<UnsplashPhoto>) : Message
}