package com.app.desafiosicredi.ui.events

import android.app.Application
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.core.utils.helpers.SingleLiveEvent
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.mapper.EventsMapper
import com.app.desafiosicredi.domain.model.events.EventsItem
import com.app.desafiosicredi.ui.events.adapter.EventsAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventsViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel(
    Application()
) {

    private val _events = MutableLiveData<Events>()
    val events: LiveData<Events>
        get() = _events

    private val _eventId = SingleLiveEvent<String>()
    val eventId: LiveData<String>
        get() = _eventId

    val eventAdapter = EventsAdapter(_eventId)

    private val mapper: EventsMapper = EventsMapper()

    fun loadEvents(items: List<EventsItem>) = eventAdapter.addItems(items)

    fun getEvents() {

        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            val eventsResponse = eventsRepository.getEvents()

            withContext(Dispatchers.Main) {
                when (eventsResponse) {
                    is Result.Success -> {
                        // errorVisibility.set(GONE)
                        _events.postValue(eventsResponse.data?.let { mapper.map(it) })
                    }
                    is Result.Error -> {
                        // errorVisibility.set(VISIBLE)
                        // errorMessage.value = R.string.server_error
                    }
                }
            }

            setProgressBarVisibility(false)
        }
    }
}
