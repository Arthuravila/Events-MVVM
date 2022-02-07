package com.app.desafiosicredi.data.repository

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.datasource.EventsRemoteDataSource
import com.app.desafiosicredi.data.model.checkin.CheckinRequestBody
import com.app.desafiosicredi.data.model.checkin.CheckinResponse
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val eventsRemoteDataSource: EventsRemoteDataSource
) : EventsRepository {

    override suspend fun getEvents(): Result<EventsResponse> {
        return eventsRemoteDataSource.getEvents()
    }

    override suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse> {
        return eventsRemoteDataSource.getEventDetail(eventId)
    }

    override suspend fun makeCheckin(requestBody: CheckinRequestBody): Result<CheckinResponse> {
        return eventsRemoteDataSource.makeCheckin(requestBody)
    }

}
