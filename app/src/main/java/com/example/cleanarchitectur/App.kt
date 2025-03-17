package com.example.cleanarchitectur

import android.app.Application
import com.example.cleanarchitectur.di.appModule
import com.example.cleanarchitectur.di.data.dataModule
import com.example.cleanarchitectur.di.domain.domainModule
import com.example.cleanarchitectur.di.presentation.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                appModule
            )
        }
    }
}