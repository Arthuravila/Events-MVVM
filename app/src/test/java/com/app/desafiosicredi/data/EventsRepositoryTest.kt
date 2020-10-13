package com.app.desafiosicredi.data

import com.app.desafiosicredi.data.eventdetail.model.CheckinRequestBody
import com.app.desafiosicredi.data.eventdetail.model.CheckinResponse
import com.app.desafiosicredi.data.eventdetail.model.EventDetail
import com.app.desafiosicredi.data.events.api.EventsApiService
import com.app.desafiosicredi.data.events.model.Events
import com.app.desafiosicredi.data.events.model.EventsItem
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.google.common.truth.Truth.assertThat
import com.haroldadmin.cnradapter.NetworkResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test

class EventsRepositoryTest {
    private val eventsApi = mockk<EventsApiService>(relaxed = true)
    private val repository = EventsRepository(eventsApi)

    private val eventId = "1"
    private val eventsExpected: Events = Events()
    private val eventItem = EventsItem(
        1602296327,
        "Evento",
        "1",
        "image.png",
        20.80,
        52.25,
        listOf(),
        30.5,
        "Teste"
    )
    private val eventDetail = EventDetail(
        1602296327,
        "Evento",
        "1",
        "image.png",
        20.80,
        52.25,
        listOf(),
        30.5,
        "Teste"
    )
    private val checkinData = CheckinRequestBody(
        "Fulano Silva",
        "teste@teste.com",
        "1"
    )
    private val successCode = CheckinResponse("200")

    @Test
    fun onSuccess_mustReturnEvents() = runBlocking {
        mockGetEventsSuccess()

        val events = repository.getEvents()

        coVerify { eventsApi.getEvents() }

        assertThat(setNetworkSuccess(eventsExpected)).isEqualTo(events)
    }

    @Test
    fun onSuccess_mustReturnEventDetail() = runBlocking {
        mockGetEventDetailSuccess()

        val eventDetailResponse = repository.getEventDetail(eventId)

        coVerify { eventsApi.getEventDetail(eventId) }

        assertThat(setNetworkSuccess(eventDetail)).isEqualTo(eventDetailResponse)
    }

    @Test
    fun onSuccess_mustReturnCheckinConfirmation() = runBlocking {
        mockMakeCheckinSuccess()

        val checkinResponse = repository.makeCheckin(checkinData)

        coVerify { eventsApi.makeCheckin(checkinData) }

        assertThat(setNetworkSuccess(successCode)).isEqualTo(checkinResponse)
    }

    @Test
    fun whenResponseNotFound_mustReturnNull() = runBlocking {
        mockApiError()

        val events = repository.getEvents()

        coVerify { eventsApi.getEvents() }

        assertThat(events).isNull()
    }

    private fun mockGetEventsSuccess() {
        eventsExpected.add(eventItem)

        coEvery { eventsApi.getEvents() }.returns(
            NetworkResponse.Success(eventsExpected, code = 200)
        )
    }

    private fun mockGetEventDetailSuccess() {
        coEvery { eventsApi.getEventDetail(eventId) }.returns(
            NetworkResponse.Success(eventDetail, code = 200)
        )
    }

    private fun mockMakeCheckinSuccess() {
        coEvery { eventsApi.makeCheckin(checkinData) }.returns(
            NetworkResponse.Success(successCode, code = 200)
        )
    }

    private fun mockApiError() {
        eventsExpected.add(eventItem)

        coEvery { eventsApi.getEvents() }.returns(
            NetworkResponse.ServerError(
                "error".toResponseBody("application/json".toMediaTypeOrNull()),
                code = 400
            )
        )
    }

    private fun setNetworkSuccess(item: Any) = NetworkResponse.Success(item, code = 200)
}
