package com.example.cleanarchitectur.domain.model.carton


data class Character(
    val id: Int? = null,
    val name: String? = null,
    val status: String? = null,
    val species: String? = null,
    val type: String? = null,
    val gender: String? = null,
    val origin: Origin? = null,
    val location: Origin? = null,
    val image: String? = null,
    val episode: List<String?>? = null,
    val url: String? = null,
    val created: String? = null
)