package com.example.cleanarchitectur.data.model.pagemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponseDto(
    @SerialName("info")
    val info: InfoDto? = null,
    @SerialName("results")
    val results: List<CharacterDto>? = null
)