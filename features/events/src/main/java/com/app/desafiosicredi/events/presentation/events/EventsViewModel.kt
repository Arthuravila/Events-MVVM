package com.app.desafiosicredi.events.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.app.desafiosicredi.common.base.BaseViewModel
import com.app.desafiosicredi.common.data.api.ApiResult
import com.app.desafiosicredi.common.data.api.model.EventsResponse
import com.app.desafiosicredi.common.data.dispatchers.EventsDispatcher
import com.app.desafiosicredi.common.domain.actions.EventsAction
import com.app.desafiosicredi.common.domain.mapper.EventsMapper
import com.app.desafiosicredi.common.domain.model.events.EventsItem
import com.app.desafiosicredi.common.utils.helpers.SingleLiveEvent
import com.app.desafiosicredi.events.presentation.events.adapter.EventsAdapter

class EventsViewModel(private val dispatcher: EventsDispatcher) :
    BaseViewModel<EventsViewState, EventsAction, ApiResult<EventsResponse>>() {

    override val internalViewState = EventsViewState()

    override fun handle(action: EventsAction) = liveData {
        when (action) {
            is EventsAction.GetEventsAction -> dispatcher.getEvents(this)
        }
    }

    override fun reduce(result: ApiResult<EventsResponse>): EventsViewState {
        return when (result) {
            is ApiResult.Loading -> internalViewState.copy(isLoading = true)
            is ApiResult.Success -> internalViewState.copy(
                isLoading = false,
                events = result.data?.let { mapper.map(it) },
                error = null
            )
            is ApiResult.Error -> internalViewState.copy(isLoading = false, error = null)
        }
    }

    private val _eventId = SingleLiveEvent<String>()
    val eventId: LiveData<String>
        get() = _eventId

    val eventAdapter = EventsAdapter(_eventId)

    private val mapper: EventsMapper = EventsMapper()

    fun loadEvents(items: ArrayList<EventsItem>) = eventAdapter.setData(items)
}
