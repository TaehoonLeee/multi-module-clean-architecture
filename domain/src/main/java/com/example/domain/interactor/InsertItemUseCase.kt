package com.example.domain.interactor

import com.example.domain.model.Item
import com.example.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {

    suspend operator fun invoke(item: Item) = itemRepository.insertItem(item)
}