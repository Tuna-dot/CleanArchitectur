package com.example.cleanarchitectur.domain.model

enum class OperationType(
    val value: String
) {
    INCREMENT("increment"),
    DECREMENT("decrement"),
    UNKNOWN("unknown");


    companion object {
        fun fromString(value: String): OperationType {
            return OperationType.entries.find { it.value == value } ?: UNKNOWN
        }
    }
}