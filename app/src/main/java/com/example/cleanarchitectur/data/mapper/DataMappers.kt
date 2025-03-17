package com.example.cleanarchitectur.data.mapper

import com.example.cleanarchitectur.data.model.ApiResponse
import com.example.cleanarchitectur.data.model.CounterDto
import com.example.cleanarchitectur.domain.model.ApiResponseEntity
import com.example.cleanarchitectur.domain.model.CounterEntity
import com.example.cleanarchitectur.domain.model.OperationType

fun counterDtoToDomain (dto: CounterDto): CounterEntity{
    return CounterEntity(
        count = dto.count,
        operationType = OperationType.fromString(dto.operationType)
    )
}

fun CounterDto.toDomain(): CounterEntity {
    return CounterEntity(
        count = count,
        operationType = OperationType.fromString(operationType)
    )
}
