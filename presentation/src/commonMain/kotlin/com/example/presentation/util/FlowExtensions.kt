package com.example.presentation.util

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun <T: Any> Flow<T>.asValue(
    initial: T,
    coroutineScope: CoroutineScope
): Value<T> = object : Value<T>() {

    private val backing: StateFlow<T> = stateIn(coroutineScope, SharingStarted.Eagerly, initial)
    override val value: T = backing.value

    override fun subscribe(observer: (T) -> Unit) {
        coroutineScope.launch {
            collect(observer)
        }
    }

    override fun unsubscribe(observer: (T) -> Unit) = Unit

}