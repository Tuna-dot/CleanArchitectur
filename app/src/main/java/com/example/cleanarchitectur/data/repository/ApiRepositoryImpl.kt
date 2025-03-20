package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.domain.repository.ApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ApiRepository {
    override suspend fun translateText(apiKey: String, text: String, targetLang: String): String {
        return withContext(ioDispatcher) {
            val response = apiService.translateText(apiKey, text, targetLang)
            response.translations?.firstOrNull()?.text ?: "Ошибка перевода"
        }
    }
}