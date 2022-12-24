package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashPhoto(
    val id: String,
    val description: String?,
    val urls: UnsplashPhotoUrls,
    val user: UnsplashUser
) {

    @Serializable
    data class UnsplashPhotoUrls(
        val raw: String,
        val full: String,
        val regular: String,
        val small: String,
        val thumb: String,
    )

    @Serializable
    data class UnsplashUser(
        val name: String,
        val username: String
    )

    override fun equals(other: Any?): Boolean {
        return id == (other as UnsplashPhoto).id
    }

    override fun hashCode(): Int {
        return super.hashCode()
    }
}