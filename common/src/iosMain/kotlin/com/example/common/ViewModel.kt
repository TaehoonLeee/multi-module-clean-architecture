package com.example.common

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual abstract class ViewModel {

	actual val coroutineScope = CoroutineScope(Dispatchers.Main.immediate + SupervisorJob())

	actual fun onCleared() {
		coroutineScope.cancel()
	}
}