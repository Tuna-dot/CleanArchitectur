package com.example.cleanarchitectur.presenter.viewmodel

import android.util.Log
import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.presenter.bases.viewmodel.BaseViewModel
import com.example.cleanarchitectur.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getTextUseCase: GetTextUseCase,
) : BaseViewModel() {

    private val _translateTextState = MutableStateFlow<UIState<String>>(UIState.Empty)
    val translateTextState: StateFlow<UIState<String>> = _translateTextState


    fun translateText(apiKey: String, text: String, targetLang: String) {
        Log.e("HomeViewModel", "translateText: ", )
        collectFlow(
            state = _translateTextState,
            request = { getTextUseCase.execute(apiKey, text, targetLang) }
        )
    }

}