package com.app.desafiosicredi.common.data.api

sealed class ApiResult<out T: Any> : Result {
    object Loading : ApiResult<Nothing>()
    data class Success<out T : Any>(val data: T? = null) : ApiResult<T>()
    data class Error(val errorMessage: String? = null) : ApiResult<Nothing>()
}