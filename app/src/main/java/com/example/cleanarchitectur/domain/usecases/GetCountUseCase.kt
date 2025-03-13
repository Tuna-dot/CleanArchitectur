package com.example.cleanarchitectur.domain.usecases

import com.example.cleanarchitectur.domain.repository.CounterRepository


class GetCountUseCase(
    private val counterRepository: CounterRepository
) {
    operator fun invoke() = counterRepository.getCounter()
}