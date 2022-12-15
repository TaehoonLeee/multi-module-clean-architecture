package com.example.domain.interactor

import com.example.domain.repository.ItemRepository

class ClearItemUseCase(
	private val itemRepository: ItemRepository
) {

	operator fun invoke() = itemRepository.clearItem()
}