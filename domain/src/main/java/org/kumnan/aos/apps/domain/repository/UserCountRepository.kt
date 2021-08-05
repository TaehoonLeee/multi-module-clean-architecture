package org.kumnan.aos.apps.domain.repository

import kotlinx.coroutines.flow.StateFlow

interface UserCountRepository {

    val userCount: StateFlow<Int>

    fun stopListening()
}