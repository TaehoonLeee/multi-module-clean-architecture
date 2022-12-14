package com.example.common

import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

fun <T: Any> Flow<T>.valueIn(
	initial: T,
	started: SharingStarted,
	coroutineScope: CoroutineScope
): Value<T> = object : Value<T>() {

	private val backing = stateIn(coroutineScope, started, initial)
	override val value: T get() = backing.value

	override fun subscribe(observer: (T) -> Unit) {
		coroutineScope.launch {
			collect(observer)
		}
	}

	override fun unsubscribe(observer: (T) -> Unit) = Unit

}