package com.example.domain.interactor

import com.example.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    operator fun invoke() = itemRepository.getItems()
}