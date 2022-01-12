package org.kumnan.aos.apps.domain.repository

import kotlinx.coroutines.flow.Flow
import org.kumnan.aos.apps.domain.model.Item

interface ItemRepository {
    fun getItems(): Flow<List<Item>>
    suspend fun insertItem(item: Item)
}