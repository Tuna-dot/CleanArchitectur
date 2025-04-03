package com.example.cleanarchitectur.presenter.bases.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectur.utils.Either
import com.example.cleanarchitectur.utils.UIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel(
) : ViewModel() {
    protected fun <T> collectFlow(
        state: MutableStateFlow<UIState<T>>,
        request: suspend () -> Flow<Either<Throwable, T>>
    ) {
        viewModelScope.launch {
            state.value = UIState.Loading
            request().collect { result ->
                when (result) {
                    is Either.Success -> state.value = UIState.Success(result.data)
                    is Either.Error -> state.value = UIState.Error(result.error.message ?: "Ошибка")
                }
            }
        }
    }
}