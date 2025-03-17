package com.example.cleanarchitectur.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Translation(
    @SerialName("detected_source_language")
    val detectedSourceLanguage: String? = null,
    @SerialName("text")
    val text: String? = null
)