package com.example.cleanarchitectur.di

import com.example.cleanarchitectur.di.corutines.dispatcherModule
import com.example.cleanarchitectur.di.data.networkModule
import com.example.cleanarchitectur.di.domain.domainModule
import com.example.cleanarchitectur.di.presentation.presentationModule

val appModule =
    listOf( domainModule, presentationModule, networkModule, dispatcherModule)