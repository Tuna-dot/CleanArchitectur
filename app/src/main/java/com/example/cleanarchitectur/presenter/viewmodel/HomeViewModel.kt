package com.example.cleanarchitectur.presenter.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getTextUseCase: GetTextUseCase,
    private val mainDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow<UIState<String>>(UIState.Empty)
    val uiState: StateFlow<UIState<String>> = _uiState

    fun translateText(apiKey: String, text: String, targetLang: String) {
        viewModelScope.launch(mainDispatcher) {
            getTextUseCase.execute(apiKey, text, targetLang).collect { result ->
                when (result) {
                    is Either.Success -> {
                        _uiState.value = UIState.Success(result.data)
                    }

                    is Either.Error -> {
                        _uiState.value = UIState.Error(result.error.message ?: "Unknown error")
                    }
                }
            }
        }
    }

    sealed class UIState<out T> {
        data object Loading : UIState<Nothing>()
        data object Empty : UIState<Nothing>()
        data class Success<T>(val data: T) : UIState<T>()
        data class Error(val message: String) : UIState<Nothing>()
    }
}