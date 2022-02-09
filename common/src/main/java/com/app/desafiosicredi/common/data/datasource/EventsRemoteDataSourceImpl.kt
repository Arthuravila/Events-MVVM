package com.app.desafiosicredi.common.data.datasource

import com.app.desafiosicredi.common.data.api.Result
import com.app.desafiosicredi.common.data.api.executeApi
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse

class EventsRemoteDataSourceImpl(
    private val apiService: com.app.desafiosicredi.common.data.api.EventsApiService
) : EventsRemoteDataSource {

    override suspend fun getEvents() = executeApi { apiService.getEvents() }

    override suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse> =
        executeApi { apiService.getEventDetail(eventId) }

    override suspend fun makeCheckin(requestBody: CheckinRequestBody): Result<CheckinResponse> =
        executeApi { apiService.makeCheckin(requestBody) }

}