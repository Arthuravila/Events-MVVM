package com.app.desafiosicredi.data.repository

import com.app.desafiosicredi.data.model.eventdetail.CheckinRequestBody
import com.app.desafiosicredi.data.model.eventdetail.CheckinResponse
import com.app.desafiosicredi.data.model.eventdetail.EventDetail
import com.app.desafiosicredi.data.api.EventsApiService
import com.app.desafiosicredi.domain.model.events.Events
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody

class EventsRepository (private val apiService: EventsApiService) {
    suspend fun getEvents(): NetworkResponse<Events, ResponseBody> {
        return apiService.getEvents()
    }

    suspend fun getEventDetail(eventId: String?): NetworkResponse<EventDetail, ResponseBody> {
        return apiService.getEventDetail(eventId)
    }

    suspend fun makeCheckin(requestBody: CheckinRequestBody): NetworkResponse<CheckinResponse, ResponseBody> {
        return apiService.makeCheckin(requestBody)
    }
}
