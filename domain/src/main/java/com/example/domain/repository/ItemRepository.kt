package com.example.domain.repository

import com.example.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun clearItem()
    suspend fun insertItem(item: Item)
}