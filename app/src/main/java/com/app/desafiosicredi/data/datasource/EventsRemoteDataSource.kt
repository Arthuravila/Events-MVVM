package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.events.CheckinRequestBody
import com.app.desafiosicredi.data.model.events.CheckinResponse
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse

interface EventsRemoteDataSource {

    suspend fun getEvents(): Result<EventsResponse>

    suspend fun getEventDetail(eventId: String?): Result<EventsItemResponse>

    suspend fun makeCheckin(requestBody: CheckinRequestBody): Result<CheckinResponse>
}