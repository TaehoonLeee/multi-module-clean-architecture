package com.example.data.entity

import com.example.domain.model.UnsplashPhoto
import kotlinx.serialization.SerialName

data class UnsplashResponse(
    val results: List<UnsplashPhoto>,
    @SerialName("total_pages")
    val totalPages: Int
)