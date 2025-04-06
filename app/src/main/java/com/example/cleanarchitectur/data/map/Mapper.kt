package com.example.cleanarchitectur.data.map

import com.example.cleanarchitectur.data.model.pagemodel.CharacterDto
import com.example.cleanarchitectur.data.model.pagemodel.LocationDto
import com.example.cleanarchitectur.data.model.pagemodel.OriginDto
import com.example.cleanarchitectur.domain.model.carton.Character
import com.example.cleanarchitectur.domain.model.carton.Origin


fun CharacterDto.toDomain() : Character {
    return Character(
        id = id,
        name = name,
        status = status,
        species = species,
        type = type,
        gender = gender,
        origin = origin?.toDomain(),
        location = location?.toDomain(),
        image = image,
        episode = episode,
        url = url,
        created = created
    )
}

fun OriginDto.toDomain(): Origin {
    return Origin(
        name = this.name,
        url = this.url
    )
}

fun LocationDto.toDomain(): Origin {
    return Origin(
        name = this.name,
        url = this.url
    )
}