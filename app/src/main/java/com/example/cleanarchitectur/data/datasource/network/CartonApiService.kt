package com.example.cleanarchitectur.data.datasource.network

import com.example.cleanarchitectur.data.model.pagemodel.BaseResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CartonApiService {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): BaseResponseDto

}