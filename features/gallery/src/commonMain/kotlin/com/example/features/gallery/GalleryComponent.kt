package com.example.features.gallery

import com.arkivanov.decompose.ComponentContext
import org.koin.core.component.KoinComponent

interface GalleryComponent {

}

class GalleryComponentImpl(
    componentContext: ComponentContext
) : GalleryComponent, ComponentContext by componentContext, KoinComponent {

}