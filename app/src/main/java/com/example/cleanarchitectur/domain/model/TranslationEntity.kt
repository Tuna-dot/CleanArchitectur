package com.example.cleanarchitectur.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
    data class TranslationEntity(
        @SerialName("detected_source_language")
        val detectedSourceLanguage: String? = null,
        @SerialName("text")
        val text: String? = null
    )