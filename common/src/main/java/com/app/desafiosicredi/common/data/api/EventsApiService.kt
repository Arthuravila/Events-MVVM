package com.app.desafiosicredi.common.data.api

import com.app.desafiosicredi.common.data.api.ApiConstants.CHECKIN
import com.app.desafiosicredi.common.data.api.ApiConstants.EVENTS
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsItemResponse
import com.app.desafiosicredi.common.data.api.model.EventsResponse

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
