package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.datasource.EmulateService
import com.example.cleanarchitectur.data.mapper.counterDtoToDomain
import com.example.cleanarchitectur.domain.model.CounterEntity
import com.example.cleanarchitectur.domain.repository.CounterRepository

class CounterRepositoryImpl (
    private val api : EmulateService
) : CounterRepository {

    override fun increment() {
        api.increment()
    }

    override fun decrement() {
        api.decrement()
    }

    override fun getCounter(): CounterEntity {
        val response = api.getCounter()
        return counterDtoToDomain(response)
    }
}