package org.kumnan.aos.apps.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.kumnan.aos.apps.data.cache.ItemDao
import org.kumnan.aos.apps.data.entity.ItemEntity
import org.kumnan.aos.apps.data.mapper.ItemMapper.mapFromDomainModel
import org.kumnan.aos.apps.data.mapper.ItemMapper.mapToDomainModel
import org.kumnan.aos.apps.data.network.ktor.ItemService
import org.kumnan.aos.apps.domain.model.Item
import org.kumnan.aos.apps.domain.model.status.Result
import org.kumnan.aos.apps.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
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

    private fun List<ItemEntity>.mapToDomainModel() =
        map { it.mapToDomainModel() }
}