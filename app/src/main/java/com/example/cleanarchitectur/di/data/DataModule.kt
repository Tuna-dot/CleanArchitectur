package com.example.cleanarchitectur.di.data

import com.example.cleanarchitectur.data.datasource.EmulateService
import com.example.cleanarchitectur.data.repository.CounterRepositoryImpl
import com.example.cleanarchitectur.domain.repository.CounterRepository
import org.koin.dsl.module

val dataModule = module {
    single<CounterRepository> { CounterRepositoryImpl(get()) }

    single { EmulateService() }
}