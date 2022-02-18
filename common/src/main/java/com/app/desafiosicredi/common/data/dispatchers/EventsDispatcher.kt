package com.app.desafiosicredi.common.data.dispatchers

import androidx.lifecycle.LiveDataScope
import com.app.desafiosicredi.common.data.api.ApiResult
import com.app.desafiosicredi.common.data.api.model.EventsResponse
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl

class EventsDispatcher(
    private val eventsRepository: EventsRepositoryImpl
) {

    suspend fun getEvents(scope: LiveDataScope<ApiResult<EventsResponse>>) {
        scope.emit(ApiResult.Loading)
        scope.emit(eventsRepository.getEvents())
    }


}