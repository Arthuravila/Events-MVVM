package com.app.desafiosicredi.data

sealed class Result<out T: Any> {
    data class Success<out T : Any>(val data: T? = null) : Result<T>()
    data class Error(val errorMessage: String? = null) : Result<Nothing>()
}