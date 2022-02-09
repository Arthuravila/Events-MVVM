package com.app.desafiosicredi.common.data.repository

import com.app.desafiosicredi.common.data.api.Result
import com.app.desafiosicredi.common.data.datasource.EventsRemoteDataSource
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.data.api.model.EventsResponse
import com.app.desafiosicredi.common.domain.repository.EventsRepository

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
