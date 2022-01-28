package com.app.desafiosicredi.ui.events

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.mapper.EventsMapper
import com.app.desafiosicredi.domain.model.events.EventsItem
import com.app.desafiosicredi.ui.events.adapter.EventsAdapter
import kotlinx.coroutines.launch

class EventsViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel(
    Application()
) {

    private val _events = MutableLiveData<List<EventsItem>>()
    val events: LiveData<List<EventsItem>>
        get() = _events

    private val _eventId = MutableLiveData<String>()
    val eventId: LiveData<String>
        get() = _eventId

    val eventAdapter = EventsAdapter(_eventId)

    private val mapper: EventsMapper = EventsMapper()

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        _events.observe({owner.lifecycle}) {
            loadEvents(it)
        }
    }

    private fun loadEvents(items: List<EventsItem>) = eventAdapter.addItems(items)

    fun getEvents() {

        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            when (val eventsResponse = eventsRepository.getEvents()) {
                is Result.Success -> {
                    // errorVisibility.set(GONE)
                    _events.postValue(eventsResponse.data?.let { mapper.map(it) }?.items)
                }
                is Result.Error -> {
                    // errorVisibility.set(VISIBLE)
                    // errorMessage.value = R.string.server_error
                }
            }
            setProgressBarVisibility(false)


        }
    }
}
