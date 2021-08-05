package org.kumnan.aos.apps.data.network.socket

import dagger.hilt.android.scopes.ViewModelScoped
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.emitter.Emitter
import java.lang.Exception

@ViewModelScoped
class SocketService : BaseObservable<SocketEventListener>() {

    lateinit var socket: Socket

    private val onConnectListener = Emitter.Listener {
        socket.emit("login", socket.id())
    }

    private val onNewUserListener = Emitter.Listener { data ->
        getListeners().forEach {
            it.onNewUserLogin(data[0] as Int)
        }
    }

    private val onUserLogOutListener = Emitter.Listener { data ->
        getListeners().forEach {
            it.onOtherUserLogOut(data[0] as Int)
        }
    }

    fun startListening() {
        try {
            socket = IO.socket(SOCKET_URL)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        socket.on(Socket.EVENT_CONNECT, onConnectListener)
            .on(EVENT_NEW_USER, onNewUserListener)
            .on(EVENT_USER_LOGOUT, onUserLogOutListener)
        socket.connect()
    }

    fun stopListening() {
        if (this::socket.isInitialized) {
            socket.disconnect()
        }
    }

    companion object {
        const val SOCKET_URL = "http://172.26.0.241:3000"
        const val EVENT_NEW_USER = "newUser"
        const val EVENT_USER_LOGOUT = "userLogout"
    }
}

interface SocketEventListener {
    fun onNewUserLogin(clientCnt: Int)
    fun onOtherUserLogOut(clientCnt: Int)
}