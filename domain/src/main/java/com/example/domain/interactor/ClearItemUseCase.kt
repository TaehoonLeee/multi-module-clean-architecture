package com.example.domain.interactor

import com.example.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ClearItemUseCase @Inject constructor(
	private val itemRepository: ItemRepository
) {

	suspend operator fun invoke() = itemRepository.clearItem()
}