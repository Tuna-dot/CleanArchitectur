package com.example.cleanarchitectur.data.model.pagemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OriginDto(
    @SerialName("name")
    val name: String? = null,
    @SerialName("url")
    val url: String? = null
)