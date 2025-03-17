package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.data.mapper.toDomain
import com.example.cleanarchitectur.data.model.TranslationRequest
import com.example.cleanarchitectur.domain.repository.ApiRepository

class ApiRepositoryImpl(private val apiService: ApiService) : ApiRepository {
    override suspend fun translateText(apiKey: String, text: String, targetLang: String): String {
        val response = apiService.translateText(apiKey, text, targetLang)
        return response.translations?.firstOrNull()?.text ?: "Ошибка перевода"
    }
}