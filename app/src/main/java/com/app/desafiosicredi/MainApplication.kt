package com.app.desafiosicredi

import android.app.Application
import com.app.desafiosicredi.common.di.dataModules
import com.app.desafiosicredi.common.di.networkModule
import com.app.desafiosicredi.events.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                listOf(
                    dataModules,
                    networkModule,
                    viewModelModule
                )
            )
        }
    }
}
