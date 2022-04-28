package com.example.presentation.ui.gallery

import androidx.paging.PagingData
import mvi.Store
import org.kumnan.aos.apps.domain.model.UnsplashPhoto

interface GalleryStore : Store<GalleryStore.Intent, GalleryStore.State, GalleryStore.Message> {

    data class State(
        val data: PagingData<UnsplashPhoto> = PagingData.empty()
    )

    sealed interface Intent {
         object FetchPhotos: Intent
    }

    sealed interface Message {
        class Fetched(val result: PagingData<UnsplashPhoto>): Message
    }
}