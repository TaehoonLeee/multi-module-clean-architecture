package com.example.common

import androidx.lifecycle.ViewModel as PlatformViewModel
import androidx.lifecycle.viewModelScope

actual abstract class ViewModel : PlatformViewModel() {

	actual val coroutineScope = viewModelScope

	public actual override fun onCleared() {
		super.onCleared()
	}
}