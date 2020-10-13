package com.app.desafiosicredi.data.events.repository

import com.app.desafiosicredi.data.eventdetail.model.CheckinRequestBody
import com.app.desafiosicredi.data.eventdetail.model.CheckinResponse
import com.app.desafiosicredi.data.eventdetail.model.EventDetail
import com.app.desafiosicredi.data.events.api.EventsApiService
import com.app.desafiosicredi.data.events.model.Events
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
