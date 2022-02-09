package com.app.desafiosicredi.common.di

import com.app.desafiosicredi.common.data.api.RetrofitInitializer
import org.koin.dsl.module

/*private val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}

val dataModules = module {
    factory<EventsRemoteDataSource> { EventsRemoteDataSourceImpl(get()) }
    single { EventsRepositoryImpl(get()) }
}*/

private val networkModule = module {
    single { RetrofitInitializer().createEventsApiService() }
}

val appModules = listOf(networkModule)
// val appModules = listOf(viewModelModule, dataModules, networkModule)
