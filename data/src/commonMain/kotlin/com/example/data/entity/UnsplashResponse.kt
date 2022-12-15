package com.example.data.entity

import com.example.domain.model.UnsplashPhoto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashResponse(
    val results: List<UnsplashPhoto>,
    @SerialName("total_pages")
    val totalPages: Int
)