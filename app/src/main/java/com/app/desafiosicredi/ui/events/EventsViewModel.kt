package com.app.desafiosicredi.ui.events

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.core.utils.Event
import com.app.desafiosicredi.data.events.model.Events
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class EventsViewModel (private val eventsRepository: EventsRepository) : BaseViewModel() {
    private val _events = MutableLiveData<Event<Events>>()
    val events = Transformations.map(_events) { it }

    private val _progressBarVisibility = MutableLiveData<Boolean>(true)
    val progressBarVisibility = Transformations.map(_progressBarVisibility) { it }

    fun getEvents() = launch {
        when (val response = eventsRepository.getEvents()) {
            is NetworkResponse.Success -> {
                _progressBarVisibility.postValue(false)
                _events.postValue(Event(response.body))
            }
            is NetworkResponse.ServerError -> {

            }
            is NetworkResponse.UnknownError -> {

            }
            else -> {

            }
        }
    }
}