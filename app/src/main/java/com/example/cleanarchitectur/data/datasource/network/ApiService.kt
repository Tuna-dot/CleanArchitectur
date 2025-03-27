package com.example.cleanarchitectur.data.datasource.network

import com.example.cleanarchitectur.data.model.TranslateDto
import kotlinx.coroutines.flow.Flow
import okhttp3.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "Content-Type: application/x-www-form-urlencoded"
    )
    @POST("v2/translate")
    suspend fun translateText(
        @Query("auth_key") apiKey: String,
        @Query("text") text: String,
        @Query("target_lang") targetLang: String
    ): retrofit2.Response<TranslateDto>
}
