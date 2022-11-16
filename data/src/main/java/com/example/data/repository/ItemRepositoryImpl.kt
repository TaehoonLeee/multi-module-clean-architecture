package com.example.data.repository

import com.example.data.cache.ItemDao
import com.example.data.entity.ItemEntity
import com.example.data.mapper.ItemMapper.mapFromDomainModel
import com.example.data.mapper.ItemMapper.mapToDomainModel
import com.example.data.network.ktor.ItemService
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import com.example.domain.model.status.Result
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemService: ItemService,
    private val itemDao: ItemDao
) : ItemRepository {

    override fun getItems(): Flow<List<Item>> = flow {
        when (val response = itemService.getItemList()) {
            is Result.Success -> {
                itemDao.insertItem(response.data)
                emit(response.data.map { it.mapToDomainModel() })
            }
            is Result.ApiError, is Result.NetworkError -> {
                emitAll(itemDao.getItemList().map { it.mapToDomainModel() })
            }
            else -> Unit
        }
    }

    override suspend fun insertItem(item: Item) {
        itemDao.insertItem(item.mapFromDomainModel())
    }

    override suspend fun clearItem() {
        itemDao.deleteAll()
    }

    private fun List<ItemEntity>.mapToDomainModel() =
        map { it.mapToDomainModel() }
}