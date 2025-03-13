package com.example.cleanarchitectur.domain.usecases

import com.example.cleanarchitectur.domain.repository.CounterRepository


class IncrementUseCase (
    private val counterRepository: CounterRepository
){
   operator fun invoke() = counterRepository.increment()
}