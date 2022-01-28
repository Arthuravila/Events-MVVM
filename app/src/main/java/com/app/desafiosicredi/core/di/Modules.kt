package com.app.desafiosicredi.core.di

import com.app.desafiosicredi.data.RetrofitInitializer
import com.app.desafiosicredi.data.datasource.RemoteDataSource
import com.app.desafiosicredi.data.datasource.RemoteDataSourceImpl
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.repository.EventsRepository
import com.app.desafiosicredi.ui.eventdetail.EventDetailViewModel
import com.app.desafiosicredi.ui.events.EventsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}

val dataModules = module {
    factory<RemoteDataSource> { RemoteDataSourceImpl(get()) }
    single { EventsRepositoryImpl(get()) }
}

private val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}

val appModules = listOf(viewModelModule, dataModules, networkModule)
