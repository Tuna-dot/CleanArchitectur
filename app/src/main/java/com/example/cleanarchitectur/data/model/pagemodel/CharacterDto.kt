package com.example.cleanarchitectur.data.model.pagemodel


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("status")
    val status: String? = null,
    @SerialName("species")
    val species: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("gender")
    val gender: String? = null,
    @SerialName("origin")
    val origin: OriginDto? = null,
    @SerialName("location")
    val location: LocationDto? = null,
    @SerialName("image")
    val image: String? = null,
    @SerialName("episode")
    val episode: List<String?>? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("created")
    val created: String? = null
)