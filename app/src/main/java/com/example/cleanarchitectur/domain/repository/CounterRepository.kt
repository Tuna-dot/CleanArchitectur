package com.example.cleanarchitectur.domain.repository

import com.example.cleanarchitectur.domain.model.CounterEntity

interface CounterRepository {
    fun increment()

    fun decrement()

    fun getCounter(): CounterEntity
}