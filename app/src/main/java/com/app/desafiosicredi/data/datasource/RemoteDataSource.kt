package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse

interface RemoteDataSource {
    suspend fun getEvents(): Result<EventsResponse>

    suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse>
}