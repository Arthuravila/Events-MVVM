package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.domain.model.events.Events

interface RemoteDataSource {

    suspend fun getEvents(): Events
}