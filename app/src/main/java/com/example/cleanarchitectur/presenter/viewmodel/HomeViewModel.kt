package com.example.cleanarchitectur.presenter.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectur.domain.model.CounterEntity
import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.domain.usecases.DecrementUseCase
import com.example.cleanarchitectur.domain.usecases.GetCountUseCase
import com.example.cleanarchitectur.domain.usecases.IncrementUseCase
import kotlinx.coroutines.launch

class HomeViewModel (
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase,
    private val getTextUseCase: GetTextUseCase
) : ViewModel() {

    private val _translation = MutableLiveData<String>()
    val translation: LiveData<String> = _translation

    private val _counter = MutableLiveData(getCountUseCase())
    val counter: LiveData<CounterEntity> = _counter

    fun translateText(apiKey:String,text: String, targetLang: String) {
        viewModelScope.launch {
            try {
                val translatedText = getTextUseCase.execute(apiKey, text, targetLang)
                _translation.value = translatedText
            } catch (e: Exception) {
                _translation.value = "Ошибка перевода"
            }
        }
    }

    fun increment() {
        incrementUseCase()
        getCounter()
    }

    fun decrement() {
        decrementUseCase()
        getCounter()
    }

    private fun getCounter() {
        _counter.value = getCountUseCase()
    }
}