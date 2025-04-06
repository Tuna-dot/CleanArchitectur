package com.example.cleanarchitectur.utils

import androidx.paging.PagingData

sealed class UIState<out T: Any > {
    data object Loading : UIState<Nothing>()
    data object Empty : UIState<Nothing>()
    data class Success<out T : Any>(val data: T) : UIState<T>()
    data class Error(val message: String) : UIState<Nothing>()
    data class PagingSuccess< T : Any>(val pagingData: PagingData<T>) : UIState<T>()
}
