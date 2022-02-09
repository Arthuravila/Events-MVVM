package com.app.desafiosicredi.common.data.api

import com.app.desafiosicredi.common.BuildConfig
import com.app.desafiosicredi.common.data.api.interceptors.AuthInterceptor
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.KoinComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer : KoinComponent {

    companion object {
        private const val TIMEOUT: Long = 60
    }

    private fun logInterceptor(): HttpLoggingInterceptor {
        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return logInterceptor
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logInterceptor())
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)

        okHttpClient.addInterceptor(AuthInterceptor())
        return okHttpClient.build()
    }

    private fun createNetworkClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    fun createEventsApiService(): EventsApiService =
        createNetworkClient().create(EventsApiService::class.java)
}
