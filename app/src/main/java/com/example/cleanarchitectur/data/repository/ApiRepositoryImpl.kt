package com.example.cleanarchitectur.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.cleanarchitectur.domain.model.carton.Character
import com.example.cleanarchitectur.data.bases.BaseRepository
import com.example.cleanarchitectur.data.datasource.network.ApiService
import com.example.cleanarchitectur.data.datasource.network.CartonApiService
import com.example.cleanarchitectur.data.map.toDomain
import com.example.cleanarchitectur.data.page.ChracterspagengSource
import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class ApiRepositoryImpl(
    private val apiService: ApiService,
    private val ioDispatcher: CoroutineDispatcher,
    private val cartonApiService: CartonApiService
) : ApiRepository, BaseRepository() {
    override suspend fun translateText(
        apiKey: String,
        text: String,
        targetLang: String
    ): Flow<Either<Throwable, String>> = makeRequest(
        request = { apiService.translateText(apiKey, text, targetLang) },
        onSuccess = { it.translations?.get(0)?.text ?: "" }
    ).flowOn(ioDispatcher)

    override suspend fun getCharacters(page: Int): Flow<PagingData<Either<Throwable, Character>>> {
        val page = Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 3,
                enablePlaceholders = false,
            ),
            pagingSourceFactory = {
                ChracterspagengSource(cartonApiService)
            }
        )
        return page.flow.map { pagingData ->
            pagingData.map { characterDto ->
                Either.Success(characterDto.toDomain())
            }
        }
    }
}