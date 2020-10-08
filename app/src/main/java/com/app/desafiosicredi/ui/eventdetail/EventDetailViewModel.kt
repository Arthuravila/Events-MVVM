package com.app.desafiosicredi.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.core.utils.Event
import com.app.desafiosicredi.data.eventdetail.model.EventDetail
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class EventDetailViewModel (private val eventsRepository: EventsRepository) : BaseViewModel() {
    private val _eventDetail = MutableLiveData<Event<EventDetail>>()
    val eventDetail = Transformations.map(_eventDetail) { it }

    private val _progressBarVisibility = MutableLiveData<Boolean>(true)
    val progressBarVisibility = Transformations.map(_progressBarVisibility) { it }

    fun getEventDetail(eventId: String?) = launch {
        when (val response = eventsRepository.getEventDetail(eventId)) {
            is NetworkResponse.Success -> {
                _progressBarVisibility.postValue(false)
                _eventDetail.postValue(Event(response.body))
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