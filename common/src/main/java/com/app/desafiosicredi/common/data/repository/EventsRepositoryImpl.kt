package com.app.desafiosicredi.common.data.repository

import com.app.desafiosicredi.common.data.api.EventsApiService
import com.app.desafiosicredi.common.data.api.ApiResult
import com.app.desafiosicredi.common.data.api.executeApi
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.domain.repository.EventsRepository

class EventsRepositoryImpl(
    private val apiService: EventsApiService
) : EventsRepository {

    override suspend fun getEvents() = executeApi { apiService.getEvents() }

    override suspend fun getEventDetail(eventId: String?): ApiResult<EventsItemResponse> =
        executeApi { apiService.getEventDetail(eventId) }

    override suspend fun makeCheckin(requestBody: CheckinRequestBody): ApiResult<CheckinResponse> =
        executeApi { apiService.makeCheckin(requestBody) }

}
