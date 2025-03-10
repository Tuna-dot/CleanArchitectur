package com.example.cleanarchitectur.data.datasource

import com.example.cleanarchitectur.data.model.CounterDto
import javax.inject.Inject

class EmulateService @Inject constructor() {
    private var count = 0
    private var isIncrement = false

    fun increment() {
        count++
        isIncrement = true
    }

    fun decrement() {
        count--
        isIncrement = false
    }

    fun getCounter(): CounterDto {
        return CounterDto(
            operationType = if (isIncrement) "INCREMENT" else "DECREMENT",
            count = count
        )
    }

}