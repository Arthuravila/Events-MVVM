package com.app.desafiosicredi.common.domain.repository

import com.app.desafiosicredi.common.data.api.ApiResult
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.data.api.model.EventsResponse

interface EventsRepository {

    suspend fun getEvents(): ApiResult<EventsResponse>

    suspend fun getEventDetail(eventId: String?): ApiResult<EventsItemResponse>

    suspend fun makeCheckin(requestBody: CheckinRequestBody): ApiResult<CheckinResponse>

}