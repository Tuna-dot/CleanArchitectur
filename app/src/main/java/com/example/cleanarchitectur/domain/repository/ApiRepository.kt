package com.example.cleanarchitectur.domain.repository


interface ApiRepository {
   suspend fun translateText(apiKey: String, text: String, targetLang: String): String
}
