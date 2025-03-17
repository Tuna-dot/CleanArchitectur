package com.example.cleanarchitectur.domain.networkusecase

import com.example.cleanarchitectur.domain.repository.ApiRepository

class GetTextUseCase(private val apiRepository: ApiRepository) {
    suspend fun execute(apiKey: String, text: String, targetLang: String): String {
        return apiRepository.translateText(apiKey, text, targetLang)
    }
}
