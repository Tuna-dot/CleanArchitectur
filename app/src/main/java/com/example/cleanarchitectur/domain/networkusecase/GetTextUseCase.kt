package com.example.cleanarchitectur.domain.networkusecase

import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.flow.Flow

class GetTextUseCase(
    private val apiRepository: ApiRepository
) {
    suspend fun execute(
        apiKey: String,
        text: String,
        targetLang: String
    ): Flow<Either<Throwable, String>> {
        return apiRepository.translateText(apiKey, text, targetLang)
    }
}
