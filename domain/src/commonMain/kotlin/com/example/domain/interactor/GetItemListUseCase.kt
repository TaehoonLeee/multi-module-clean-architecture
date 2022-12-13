package com.example.domain.interactor

import com.example.domain.repository.ItemRepository

class GetItemListUseCase(
    private val itemRepository: ItemRepository
) {
    operator fun invoke() = itemRepository.getItems()
}