package com.example.cleanarchitectur.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cleanarchitectur.domain.model.CounterEntity
import com.example.cleanarchitectur.domain.usecases.DecrementUseCase
import com.example.cleanarchitectur.domain.usecases.GetCountUseCase
import com.example.cleanarchitectur.domain.usecases.IncrementUseCase

class HomeViewModel (
    private val incrementUseCase: IncrementUseCase,
    private val decrementUseCase: DecrementUseCase,
    private val getCountUseCase: GetCountUseCase
) : ViewModel() {

    private val _counter = MutableLiveData(getCountUseCase())
    val counter: LiveData<CounterEntity> = _counter

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