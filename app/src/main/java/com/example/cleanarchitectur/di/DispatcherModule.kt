package com.example.cleanarchitectur.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single<CoroutineDispatcher>(named("IO")) { Dispatchers.IO }
    single<CoroutineDispatcher>(named("Main")) { Dispatchers.Main }
    single<CoroutineDispatcher> { Dispatchers.Default }
    single<CoroutineDispatcher> { Dispatchers.Unconfined }
}