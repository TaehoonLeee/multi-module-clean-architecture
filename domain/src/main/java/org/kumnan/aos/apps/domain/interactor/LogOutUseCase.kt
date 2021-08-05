package org.kumnan.aos.apps.domain.interactor

import org.kumnan.aos.apps.domain.repository.UserCountRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val userCountRepository: UserCountRepository
) {

    fun execute() = userCountRepository.stopListening()
}