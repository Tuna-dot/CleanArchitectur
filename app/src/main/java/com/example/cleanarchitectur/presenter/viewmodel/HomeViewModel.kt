package com.example.cleanarchitectur.presenter.viewmodel

import com.example.cleanarchitectur.domain.networkusecase.GetCharacterUseCase
import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.presenter.bases.viewmodel.BaseViewModel
import com.example.cleanarchitectur.utils.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import com.example.cleanarchitectur.domain.model.carton.Character
import kotlinx.coroutines.flow.StateFlow

class HomeViewModel(
    private val getTextUseCase: GetTextUseCase,
    private val getCharacterUseCase: GetCharacterUseCase
) : BaseViewModel() {

    private val _characterState = MutableStateFlow<UIState<Character>>(UIState.Empty)
    val characterState: StateFlow<UIState<Character>> = _characterState

    private val _translateTextState = MutableStateFlow<UIState<String>>(UIState.Empty)
    val translateTextState: StateFlow<UIState<String>> = _translateTextState

    fun getCharacters(page : Int) {
        collectFlowPagingData(
            state = _characterState,
            request = { getCharacterUseCase.invoke(page) }
        )
    }

    fun translateText(apiKey: String, text: String, targetLang: String) {
        collectFlow(
            state = _translateTextState,
            request = { getTextUseCase.execute(apiKey, text, targetLang) }
        )
    }

}