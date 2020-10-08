package com.app.desafiosicredi.data

import java.io.IOException
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authenticatedRequest = request.newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
        return chain.proceed(authenticatedRequest)
    }
}
