package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ApiRepository {
    override suspend fun translateText(
        apiKey: String,
        text: String,
        targetLang: String
    ): Flow<Either<Throwable, String>> = flow {
        try {
            val response = apiService.translateText(apiKey, text, targetLang)
            if (response.isSuccessful && response.body() != null) {
                val translatedText = response.body()?.translations?.firstOrNull()?.text.orEmpty()
                emit(Either.Success(translatedText))
            } else {
                emit(Either.Error(Exception("Ошибка сети")))
            }
        } catch (e: Exception) {
            emit(Either.Error(e))
        }
    }.flowOn(ioDispatcher)
}