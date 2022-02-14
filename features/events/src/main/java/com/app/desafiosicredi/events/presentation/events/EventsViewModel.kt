package com.app.desafiosicredi.events.presentation.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.common.data.api.Result
import com.app.desafiosicredi.common.base.BaseViewModel
import com.app.desafiosicredi.common.utils.helpers.SingleLiveEvent
import com.app.desafiosicredi.common.domain.model.events.Events
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.common.domain.mapper.EventsMapper
import com.app.desafiosicredi.common.domain.model.events.EventsItem
import com.app.desafiosicredi.events.presentation.events.adapter.EventsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel() {

    private val _events = SingleLiveEvent<Events>()
    val events: LiveData<Events>
        get() = _events

    private val _eventId = SingleLiveEvent<String>()
    val eventId: LiveData<String>
        get() = _eventId

    val eventAdapter = EventsAdapter(_eventId)

    private val mapper: EventsMapper = EventsMapper()

    fun loadEvents(items: ArrayList<EventsItem>) = eventAdapter.setData(items)

    fun getEvents() {

        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            val eventsResponse = eventsRepository.getEvents()

            withContext(Dispatchers.Main) {
                when (eventsResponse) {
                    is Result.Success -> {
                        setErrorState(false)
                        _events.postValue(eventsResponse.data?.let { mapper.map(it) })
                    }
                    is Result.Error -> {
                        setErrorState(true, eventsResponse.errorMessage)
                    }
                }
            }
            setProgressBarVisibility(false)
        }
    }
}
