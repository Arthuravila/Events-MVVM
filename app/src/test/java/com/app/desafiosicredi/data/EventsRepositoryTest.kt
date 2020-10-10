package com.app.desafiosicredi.data

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

    @Test
    fun mustCallApi() = runBlocking {
        mockApiSuccess()

        repository.getEvents()

        coVerify { eventsApi.getEvents() }
    }

    @Test
    fun onSuccess_mustReturnEvents() = runBlocking {
        mockApiSuccess()

        val events = repository.getEvents()

        coVerify { eventsApi.getEvents() }

        assertThat(eventsExpected).isEqualTo(events)
    }

    @Test
    fun whenOrderNotFound_mustReturnNull() = runBlocking {
        mockApiError()

        val events = repository.getEvents()

        coVerify { eventsApi.getEvents() }

        assertThat(events).isNull()
    }

    private fun mockApiSuccess() {
        eventsExpected.add(eventItem)

        coEvery { eventsApi.getEvents() }.returns(
            NetworkResponse.Success(eventsExpected, code = 200)
        )
    }

    private fun mockApiError() {
        eventsExpected.add(eventItem)

        coEvery { eventsApi.getEvents() }.returns(
            NetworkResponse.ServerError("error".toResponseBody("application/json".toMediaTypeOrNull()), code = 400)
        )
    }


}