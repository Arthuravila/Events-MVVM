package com.app.desafiosicredi.domain.repository

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.events.EventsResponse

interface EventsRepository {

    suspend fun getEvents(): Result<EventsResponse>
}