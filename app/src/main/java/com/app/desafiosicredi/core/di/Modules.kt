package com.app.desafiosicredi.core.di

import com.app.desafiosicredi.data.RetrofitInitializer
import com.app.desafiosicredi.data.datasource.EventsRemoteDataSource
import com.app.desafiosicredi.data.datasource.EventsRemoteDataSourceImpl
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.ui.eventdetail.EventDetailViewModel
import com.app.desafiosicredi.ui.events.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}

val dataModules = module {
    factory<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single { EventsRepositoryImpl(get()) }
}

private val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}

val appModules = listOf(viewModelModule, dataModules, networkModule)
