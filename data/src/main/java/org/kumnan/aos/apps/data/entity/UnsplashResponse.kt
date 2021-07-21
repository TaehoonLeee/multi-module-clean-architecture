package org.kumnan.aos.apps.data.entity

import com.google.gson.annotations.SerializedName
import org.kumnan.aos.apps.domain.entity.UnsplashPhoto

data class UnsplashResponse(
    val results: List<UnsplashPhoto>,
    @SerializedName("total_pages")
    val totalPages: Int
)