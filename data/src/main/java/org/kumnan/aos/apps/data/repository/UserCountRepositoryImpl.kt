package org.kumnan.aos.apps.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.kumnan.aos.apps.data.network.socket.SocketEventListener
import org.kumnan.aos.apps.data.network.socket.SocketService
import org.kumnan.aos.apps.domain.repository.UserCountRepository
import javax.inject.Inject

@ViewModelScoped
class UserCountRepositoryImpl @Inject constructor(
    private val socketService: SocketService
) : SocketEventListener, UserCountRepository {

    private val _userCount: MutableStateFlow<Int> = MutableStateFlow(0)
    override val userCount: StateFlow<Int> get() = _userCount

    init {
        socketService.registerListener(this)
        socketService.startListening()
    }

    override fun stopListening() {
        socketService.unRegisterListener(this)
        socketService.stopListening()
    }

    override fun onNewUserLogin(clientCnt: Int) {
        _userCount.value = clientCnt
    }

    override fun onOtherUserLogOut(clientCnt: Int) {
        _userCount.value = clientCnt
    }
}