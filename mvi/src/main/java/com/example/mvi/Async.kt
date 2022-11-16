package com.example.mvi

sealed class Async<out T> {
	object Loading : Async<Nothing>()
	data class Success<out T>(val data: T) : Async<T>()
}