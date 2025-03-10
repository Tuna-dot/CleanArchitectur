package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.datasource.EmulateService
import com.example.cleanarchitectur.domain.model.CounterEntity
import com.example.cleanarchitectur.domain.model.OperationType
import com.example.cleanarchitectur.domain.repository.CounterRepository
import javax.inject.Inject

class CounterRepositoryImpl @Inject constructor(
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
        return CounterEntity(
            count = response.count,
            operationType = OperationType.fromString(response.operationType)
        )
    }
}