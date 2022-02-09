package com.app.desafiosicredi.common.di

import com.app.desafiosicredi.common.data.api.RetrofitInitializer
import com.app.desafiosicredi.common.data.datasource.EventsRemoteDataSource
import com.app.desafiosicredi.common.data.datasource.EventsRemoteDataSourceImpl
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl
import org.koin.dsl.module

val dataModules = module {
    factory<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single { EventsRepositoryImpl(get()) }
}

val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}
