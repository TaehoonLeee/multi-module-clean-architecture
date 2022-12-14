package com.example.features.gallery

import app.cash.paging.PagingData
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import com.arkivanov.essenty.instancekeeper.getOrCreate
import com.example.domain.model.UnsplashPhoto
import kotlinx.coroutines.flow.Flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

interface GalleryComponent {

    val state: Value<GalleryComponentState>

    data class GalleryComponentState(
        val searchResult: Flow<PagingData<UnsplashPhoto>>
    )
}

class GalleryComponentImpl(
    componentContext: ComponentContext
) : GalleryComponent, ComponentContext by componentContext, KoinComponent {

    private val viewModel = instanceKeeper.getOrCreate { GalleryViewModel(get()) }

    override val state: Value<GalleryComponent.GalleryComponentState> = viewModel.state

}