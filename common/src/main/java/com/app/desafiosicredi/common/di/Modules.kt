package com.app.desafiosicredi.common.di

import com.app.desafiosicredi.common.data.api.RetrofitInitializer
import com.app.desafiosicredi.common.data.dispatchers.EventsDispatcher
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl
import org.koin.dsl.module

val dataModules = module {
    factory { EventsDispatcher(get()) }
    single { EventsRepositoryImpl(get()) }
}

val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}
