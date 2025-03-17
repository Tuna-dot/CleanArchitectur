package com.example.cleanarchitectur.di.domain

import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import com.example.cleanarchitectur.domain.usecases.DecrementUseCase
import com.example.cleanarchitectur.domain.usecases.GetCountUseCase
import com.example.cleanarchitectur.domain.usecases.IncrementUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { IncrementUseCase(get()) }
    factory { DecrementUseCase(get()) }
    factory { GetCountUseCase(get()) }
    single { GetTextUseCase(apiRepository = get()) }

}