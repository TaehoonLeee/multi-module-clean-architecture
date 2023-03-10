package com.example.common

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel() {
	val coroutineScope: CoroutineScope

	fun onCleared()
}