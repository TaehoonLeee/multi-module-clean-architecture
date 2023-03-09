package com.example.common

import androidx.lifecycle.viewModelScope

abstract class ViewModel : androidx.lifecycle.ViewModel() {

	actual val coroutineScope = viewModelScope

	public actual override fun onCleared() {
		super.onCleared()
	}
}