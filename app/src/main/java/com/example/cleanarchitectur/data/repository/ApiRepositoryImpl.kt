package com.example.cleanarchitectur.data.repository

import com.example.cleanarchitectur.data.bases.BaseRepository
import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher
) : ApiRepository, BaseRepository() {
    override suspend fun translateText(
        apiKey: String,
        text: String,
        targetLang: String
    ): Flow<Either<Throwable, String>> = makeRequest(
        request = { apiService.translateText(apiKey, text, targetLang) },
        onSuccess = { it.translations?.get(0)?.text ?: "" }
    ).flowOn(ioDispatcher)
}