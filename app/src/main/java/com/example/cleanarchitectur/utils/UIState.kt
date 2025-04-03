package com.example.cleanarchitectur.utils

sealed class UIState<out T> {
    data object Loading : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
}