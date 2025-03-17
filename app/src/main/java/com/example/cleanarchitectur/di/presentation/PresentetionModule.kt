package com.example.cleanarchitectur.di.presentation

import com.example.cleanarchitectur.presenter.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel {
        HomeViewModel(
            incrementUseCase = get(),
            decrementUseCase = get(),
            getCountUseCase = get(),
            getTextUseCase = get()
        )
    }
}