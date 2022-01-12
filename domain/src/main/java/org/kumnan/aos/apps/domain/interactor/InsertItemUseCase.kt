package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.model.Item
import org.kumnan.aos.apps.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertItemUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {

    suspend operator fun invoke(item: Item) = itemRepository.insertItem(item)
}