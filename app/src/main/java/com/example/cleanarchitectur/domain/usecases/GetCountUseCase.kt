package com.example.cleanarchitectur.domain.usecases

import com.example.cleanarchitectur.domain.repository.CounterRepository
import javax.inject.Inject

class GetCountUseCase @Inject constructor(
    private val counterRepository: CounterRepository
) {
    fun getCount() = counterRepository.getCounter()
}