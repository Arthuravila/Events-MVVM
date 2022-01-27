package com.app.desafiosicredi.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.core.utils.helpers.Event
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.data.repository.EventsRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class EventsViewModel(private val eventsRepository: EventsRepository) : BaseViewModel() {
    private val _events = MutableLiveData<Event<Events>>()
    val events = Transformations.map(_events) { it }

    fun getEvents() = launch {
        if (progressBarVisibility.value == false) setProgressBarVisibility(true)
        when (val response = eventsRepository.getEvents()) {
            is NetworkResponse.Success -> {
                _events.postValue(Event(response.body))
            }
            is NetworkResponse.ServerError -> {
                setServerError(true)
            }
            else -> {
                setUnknownError(true)
            }
        }
        setProgressBarVisibility(false)
    }
}
