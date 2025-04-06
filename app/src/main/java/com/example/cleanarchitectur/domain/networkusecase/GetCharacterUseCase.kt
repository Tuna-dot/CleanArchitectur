package com.example.cleanarchitectur.domain.networkusecase

import androidx.paging.PagingData
import com.example.cleanarchitectur.domain.model.carton.Character
import com.example.cleanarchitectur.domain.repository.ApiRepository
import com.example.cleanarchitectur.utils.Either
import kotlinx.coroutines.flow.Flow


class GetCharacterUseCase(
    private val api: ApiRepository
) {
    suspend operator fun invoke(page: Int): Flow<PagingData<Either<Throwable, Character>>> {
        return api.getCharacters(page)
    }
}