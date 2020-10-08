package com.app.desafiosicredi.core.di

import com.app.desafiosicredi.data.RetrofitInitializer
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.app.desafiosicredi.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

private val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}

private val repositoryModule = module {
    factory { EventsRepository(get()) }
}

val appModules = listOf(viewModelModule, repositoryModule, networkModule)