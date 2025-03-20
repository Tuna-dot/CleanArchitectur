package com.example.cleanarchitectur.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher> { Dispatchers.IO }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single<CoroutineDispatcher> { Dispatchers.Default }
    single<CoroutineDispatcher> { Dispatchers.Unconfined }
}