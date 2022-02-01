package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.api.EventsApiService
import com.app.desafiosicredi.data.executeApi
import com.app.desafiosicredi.data.model.events.EventsItemResponse

class EventsRemoteDataSourceImpl(
    private val apiService: EventsApiService
) : EventsRemoteDataSource {

    override suspend fun getEvents() = executeApi { apiService.getEvents() }

    override suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse> =
        executeApi { apiService.getEventDetail(eventId) }

}