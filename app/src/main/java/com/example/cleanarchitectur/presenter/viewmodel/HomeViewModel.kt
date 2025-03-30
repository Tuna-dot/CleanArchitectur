package com.example.cleanarchitectur.presenter.viewmodel


import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.presenter.bases.viewmodel.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getTextUseCase: GetTextUseCase,
    mainDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher) {

    private val _translateTextState = MutableStateFlow<UIState<String>>(UIState.Empty)
    val translateTextState: StateFlow<UIState<String>> = _translateTextState


    fun translateText(apiKey: String, text: String, targetLang: String) {
        collectFlow(
            state = _translateTextState,
            request = { getTextUseCase.execute(apiKey, text, targetLang) }
        )
    }

    sealed class UIState<out T> {
        data object Loading : UIState<Nothing>()
        data object Empty : UIState<Nothing>()
        data class Success<T>(val data: T) : UIState<T>()
        data class Error(val message: String) : UIState<Nothing>()
    }
}