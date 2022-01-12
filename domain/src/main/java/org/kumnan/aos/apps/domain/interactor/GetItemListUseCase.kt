package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.repository.ItemRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    operator fun invoke() = itemRepository.getItems()
}