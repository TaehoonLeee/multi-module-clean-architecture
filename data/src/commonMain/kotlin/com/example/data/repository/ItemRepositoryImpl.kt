package com.example.data.repository

import com.example.data.ItemDatabase
import com.example.data.cache.DatabaseDriverFactory
import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import com.squareup.sqldelight.Query
import com.squareup.sqldelight.runtime.coroutines.asFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import com.example.data.Item as ItemEntity

class ItemRepositoryImpl(
    databaseDriverFactory: DatabaseDriverFactory
) : ItemRepository {

    private val database = ItemDatabase(databaseDriverFactory.createDriver())
    private val simpleMapper: (Long, String, String) -> Item = { _, description, title ->
        Item(title, description)
    }

    override fun getItems(): Flow<List<Item>> {
        return database.itemQueries
            .selectAll(simpleMapper)
            .asFlow()
            .map(Query<Item>::executeAsList)
    }

    override fun insertItem(item: Item) {
        database.itemQueries.insert(null, item.description, item.title)
    }
}