package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.repository.UserCountRepository
import javax.inject.Inject

class GetUserCountUseCase @Inject constructor(
    private val userCountRepository: UserCountRepository
){

    operator fun invoke() = userCountRepository.userCount
}