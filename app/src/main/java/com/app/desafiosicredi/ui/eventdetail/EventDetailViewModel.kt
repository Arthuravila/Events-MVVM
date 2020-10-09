package com.app.desafiosicredi.ui.eventdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.core.utils.helpers.Event
import com.app.desafiosicredi.data.eventdetail.model.CheckinRequestBody
import com.app.desafiosicredi.data.eventdetail.model.CheckinResponse
import com.app.desafiosicredi.data.eventdetail.model.EventDetail
import com.app.desafiosicredi.data.events.repository.EventsRepository
import com.haroldadmin.cnradapter.NetworkResponse
import kotlinx.coroutines.launch

class EventDetailViewModel (private val eventsRepository: EventsRepository) : BaseViewModel() {
    private val _eventDetail = MutableLiveData<Event<EventDetail>>()
    val eventDetail = Transformations.map(_eventDetail) { it }

    private val _checkinResponse = MutableLiveData<CheckinResponse>()
    val checkinResponse = Transformations.map(_checkinResponse) { it }

    fun getEventDetail(eventId: String?) = launch {
        when (val response = eventsRepository.getEventDetail(eventId)) {
            is NetworkResponse.Success -> {
                _eventDetail.postValue(Event(response.body))
            }
            is NetworkResponse.ServerError -> {

            }
            is NetworkResponse.UnknownError -> {

            }
            else -> {

            }
        }
        setProgressBarVisibility(false)
    }


    fun makeCheckin(name: String, email: String, eventId: String?) = launch {
        when (val response =  eventsRepository.makeCheckin(CheckinRequestBody(name, email, eventId)) ) {
            is NetworkResponse.Success -> {

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
