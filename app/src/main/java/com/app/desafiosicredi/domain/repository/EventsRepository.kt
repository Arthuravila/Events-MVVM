package com.app.desafiosicredi.domain.repository

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.checkin.CheckinRequestBody
import com.app.desafiosicredi.data.model.checkin.CheckinResponse
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse

interface EventsRepository {

    suspend fun getEvents(): Result<EventsResponse>

    suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse>

    suspend fun makeCheckin(requestBody: CheckinRequestBody): Result<CheckinResponse>

}