package com.example.cleanarchitectur.di

import com.example.cleanarchitectur.di.data.dataModule
import com.example.cleanarchitectur.di.domain.domainModule
import com.example.cleanarchitectur.di.presentation.presentationModule

val appModule =
    listOf(dataModule, domainModule, presentationModule, networkModule, dispatcherModule)