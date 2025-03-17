package com.example.cleanarchitectur.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponseEntity(
    @SerialName("translations")
    val translations: List<TranslationEntity?>? = null
)