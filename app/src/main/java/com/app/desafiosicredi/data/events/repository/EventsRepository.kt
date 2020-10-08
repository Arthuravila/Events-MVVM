package com.app.desafiosicredi.data.events.repository

import com.app.desafiosicredi.data.events.api.EventsApiService
import com.app.desafiosicredi.data.events.model.Events
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody

class EventsRepository (private val apiService: EventsApiService) {
    suspend fun getEvents(): NetworkResponse<Events, ResponseBody> {
        return apiService.getEvents()
    }
}