package com.example.cleanarchitectur.data.model.pagemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDto(
    @SerialName("count")
    val count: Int? = null,
    @SerialName("pages")
    val pages: Int? = null,
    @SerialName("next")
    val next: String? = null,
    @SerialName("prev")
    val prev: String? = ""
)