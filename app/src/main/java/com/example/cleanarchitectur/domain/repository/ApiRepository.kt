package com.example.cleanarchitectur.domain.repository

import androidx.paging.PagingData
import com.example.cleanarchitectur.domain.model.carton.Character
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.flow.Flow


interface ApiRepository {
    suspend fun translateText(
        apiKey: String,
        text: String,
        targetLang: String
    ): Flow<Either<Throwable, String>>

    suspend fun getCharacters(page: Int): Flow<PagingData<Either<Throwable, Character>>>

}