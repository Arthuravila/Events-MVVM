package com.app.desafiosicredi.data.repository

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.datasource.RemoteDataSource
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource
) : EventsRepository {

    override suspend fun getEvents(): Result<EventsResponse> {
        return remoteDataSource.getEvents()
    }


/*    suspend fun getEventDetail(eventId: String?): NetworkResponse<EventDetail, ResponseBody> {
        return apiService.getEventDetail(eventId)
    }

    suspend fun makeCheckin(requestBody: CheckinRequestBody): NetworkResponse<CheckinResponse, ResponseBody> {
        return apiService.makeCheckin(requestBody)
    }*/
}
