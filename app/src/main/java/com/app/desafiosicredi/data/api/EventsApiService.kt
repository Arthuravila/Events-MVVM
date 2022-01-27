package com.app.desafiosicredi.data.api

import com.app.desafiosicredi.data.ApiEndPoint.CHECKIN
import com.app.desafiosicredi.data.ApiEndPoint.EVENTS
import com.app.desafiosicredi.data.model.eventdetail.CheckinRequestBody
import com.app.desafiosicredi.data.model.eventdetail.CheckinResponse
import com.app.desafiosicredi.data.model.eventdetail.EventDetail
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.haroldadmin.cnradapter.NetworkResponse
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventsApiService {
    @GET(EVENTS)
    suspend fun getEvents(): NetworkResponse<EventsResponse, ResponseBody>

    @GET("$EVENTS{eventId}")
    suspend fun getEventDetail(
        @Path("eventId") eventId: String?
    ): NetworkResponse<EventDetail, ResponseBody>

    @POST(CHECKIN)
    suspend fun makeCheckin(
        @Body requestBody: CheckinRequestBody
    ): NetworkResponse<CheckinResponse, ResponseBody>
}
