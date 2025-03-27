package com.example.cleanarchitectur.utils

sealed class Either<out L, out R> {
    data class Error<L>(val error: L) : Either<L, Nothing>()
    data class Success<R>(val data: R) : Either<Nothing, R>()
}