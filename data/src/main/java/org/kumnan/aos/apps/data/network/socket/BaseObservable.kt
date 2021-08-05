package org.kumnan.aos.apps.data.network.socket

import java.util.*
import java.util.concurrent.ConcurrentHashMap

open class BaseObservable<LISTENER> {

    private val listeners = Collections.newSetFromMap(
        ConcurrentHashMap<LISTENER, Boolean>(1)
    )

    fun registerListener(listener: LISTENER) = listeners.add(listener)

    fun unRegisterListener(listener: LISTENER) = listeners.remove(listener)

    fun getListeners(): Set<LISTENER> = listeners
}