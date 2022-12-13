package com.example.domain.interactor

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository

class InsertItemUseCase(
    private val itemRepository: ItemRepository
) {

    operator fun invoke(item: Item) = itemRepository.insertItem(item)
}