package org.kumnan.aos.apps.data.mapper

import org.kumnan.aos.apps.data.entity.ItemEntity
import org.kumnan.aos.apps.domain.model.Item

object ItemMapper : Mapper<ItemEntity, Item> {
    override fun ItemEntity.mapToDomainModel(): Item =
        Item(title, description)

    override fun Item.mapFromDomainModel(): ItemEntity =
        ItemEntity(title, description)
}