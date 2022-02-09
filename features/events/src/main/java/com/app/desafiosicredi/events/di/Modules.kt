package com.app.desafiosicredi.events.di

import com.app.desafiosicredi.events.presentation.eventdetail.EventDetailViewModel
import com.app.desafiosicredi.events.presentation.events.EventsViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { EventDetailViewModel(get()) }
}