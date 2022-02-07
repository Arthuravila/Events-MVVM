package com.app.desafiosicredi.ui.eventdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.events.CheckinRequestBody
import com.app.desafiosicredi.data.model.events.CheckinResponse
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.mapper.toEventsItem
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.domain.model.events.EventsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventDetailViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel() {

    private val _eventDetail = MutableLiveData<EventsItem>()
    val eventDetail: LiveData<EventsItem>
        get() = _eventDetail

    private val _checkinResponse = MutableLiveData<CheckinResponse>()
    val checkinResponse: LiveData<CheckinResponse>
        get() = _checkinResponse

    fun getEventDetail(eventId: String?) {

        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            val eventDetailResponse = eventsRepository.getEventDetail(eventId)

            withContext(Dispatchers.Main) {
                when (eventDetailResponse) {
                    is Result.Success -> {
                        setErrorState(false)
                        _eventDetail.postValue(eventDetailResponse.data?.toEventsItem())
                    }
                    is Result.Error -> {
                        setErrorState(true, eventDetailResponse.errorMessage)
                    }
                }
            }

            setProgressBarVisibility(false)
        }

    }


    fun makeCheckin(name: String, email: String, eventId: String?) {

        viewModelScope.launch {
            val checkinResponse =
                eventsRepository.makeCheckin(CheckinRequestBody(name, email, eventId))

            withContext(Dispatchers.Main) {
                when (checkinResponse) {
                    is Result.Success -> {
                        setErrorState(false)
                        _checkinResponse.postValue(checkinResponse.data)
                    }
                    is Result.Error -> {
                        setErrorState(false, checkinResponse.errorMessage)
                    }
                }
            }
        }
    }
}
