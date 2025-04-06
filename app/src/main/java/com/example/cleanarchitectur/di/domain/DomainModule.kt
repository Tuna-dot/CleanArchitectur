package com.example.cleanarchitectur.di.domain

import com.example.cleanarchitectur.domain.networkusecase.GetCharacterUseCase
import com.example.cleanarchitectur.domain.networkusecase.GetTextUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetTextUseCase(apiRepository = get()) }
    single { GetCharacterUseCase(api = get()) }
}