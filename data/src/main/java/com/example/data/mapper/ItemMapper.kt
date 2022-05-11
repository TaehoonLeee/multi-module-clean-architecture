package com.example.data.mapper

import com.example.data.entity.ItemEntity
import com.example.domain.model.Item

object ItemMapper : Mapper<ItemEntity, Item> {
    override fun ItemEntity.mapToDomainModel(): Item =
        Item(title, description)

    override fun Item.mapFromDomainModel(): ItemEntity =
        ItemEntity(title, description)
}