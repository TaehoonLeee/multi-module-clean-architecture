package com.example.data.network

import com.example.data.entity.UnsplashResponse
import com.example.domain.model.status.Result

interface UnsplashService {

    suspend fun searchPhotos(query: String, page: Int = 1, perPage: Int = 20): Result<UnsplashResponse>
}