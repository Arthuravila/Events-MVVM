package com.app.desafiosicredi.data

import retrofit2.Response

suspend fun <T : Any> executeApi(call: suspend () -> Response<T>): Result<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            Result.Success(response.body())
        } else {
            Result.Error(response.message())
        }

    } catch (e: Exception) {
        Result.Error(e.message)
    }
}