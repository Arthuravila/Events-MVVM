package com.app.desafiosicredi

import android.app.Application
import com.app.desafiosicredi.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(listOf(viewModelModule))
        }
    }

}