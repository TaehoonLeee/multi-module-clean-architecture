package com.example.presentation.ui.market

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import org.kumnan.aos.apps.domain.model.Item
import javax.inject.Inject

data class MarketState @Inject constructor(
	val items: List<Item>
) {
	@Module
	@InstallIn(ActivityRetainedComponent::class)
	object Injection {

		@Provides
		fun provideItems() = emptyList<Item>()
	}
}

sealed interface MarketIntent {
	object ObserveItems : MarketIntent
	class InsertItem(val item: Item) : MarketIntent
}

sealed interface MarketMessage {
	class Fetched(val data: List<Item>) : MarketMessage
}