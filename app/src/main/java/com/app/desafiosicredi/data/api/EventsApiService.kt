package com.app.desafiosicredi.data.api

import com.app.desafiosicredi.data.ApiEndPoint.CHECKIN
import com.app.desafiosicredi.data.ApiEndPoint.EVENTS
import com.app.desafiosicredi.data.model.events.CheckinRequestBody
import com.app.desafiosicredi.data.model.events.CheckinResponse
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApiService {
    @GET(EVENTS)
    suspend fun getEvents(): Response<EventsResponse>

    @GET("$EVENTS{eventId}")
    suspend fun getEventDetail(
        @Path("eventId") eventId: String?
    ): Response<EventsItemResponse>

    @POST(CHECKIN)
    suspend fun makeCheckin(
        @Body requestBody: CheckinRequestBody
    ): Response<CheckinResponse>
}
