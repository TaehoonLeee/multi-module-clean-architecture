package com.example.presentation.gallery

import com.arkivanov.decompose.ComponentContext

interface GalleryComponent {

}

class GalleryComponentImpl(
    componentContext: ComponentContext
) : GalleryComponent, ComponentContext by componentContext