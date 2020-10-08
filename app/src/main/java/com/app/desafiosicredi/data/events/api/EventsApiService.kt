package com.app.desafiosicredi.data.events.api

import com.app.desafiosicredi.data.ApiEndPoint.EVENTS
import com.app.desafiosicredi.data.events.model.Events
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.http.GET

interface EventsApiService {
    @GET(EVENTS)
    suspend fun getEvents(): NetworkResponse<Events, ResponseBody>
}