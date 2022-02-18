package com.app.desafiosicredi.common.data.api

import retrofit2.Response

suspend fun <T : Any> executeApi(call: suspend () -> Response<T>): ApiResult<T> {
    return try {
        val response = call.invoke()
        if (response.isSuccessful) {
            ApiResult.Success(response.body())
        } else {
            ApiResult.Error(response.message())
        }

    } catch (e: Exception) {
        ApiResult.Error(e.message)
    }
}