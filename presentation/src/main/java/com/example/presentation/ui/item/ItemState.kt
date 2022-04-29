package com.example.presentation.ui.item

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.kumnan.aos.apps.domain.model.Item
import javax.inject.Inject

data class ItemState @Inject constructor(
	val items: List<Item>
) {
	@Module
	@InstallIn(ActivityRetainedComponent::class)
	object Injection {

		@Provides
		fun provideItems() = emptyList<Item>()
	}
}

sealed interface ItemIntent {
	object ObserveItems : ItemIntent
	class InsertItem(val item: Item) : ItemIntent
}

sealed interface ItemMessage {
	class Fetched(val data: List<Item>) : ItemMessage
}
