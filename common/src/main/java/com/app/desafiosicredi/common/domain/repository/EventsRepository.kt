package com.app.desafiosicredi.common.domain.repository

import com.app.desafiosicredi.common.data.api.Result
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.data.api.model.EventsResponse

interface EventsRepository {

    suspend fun getEvents(): Result<EventsResponse>

    suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse>

    suspend fun makeCheckin(requestBody: CheckinRequestBody): Result<CheckinResponse>

}