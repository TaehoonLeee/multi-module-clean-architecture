package com.example.domain.repository

import com.example.domain.model.Item
import kotlinx.coroutines.flow.Flow

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    fun insertItem(item: Item)
    fun clearItem()
}