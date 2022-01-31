package com.app.desafiosicredi.ui.eventdetail

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.data.model.checkin.CheckinResponse
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.mapper.toEventsItem
import com.app.desafiosicredi.domain.model.events.EventsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventDetailViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel(
    Application()
) {
    private val _eventDetail = MutableLiveData<EventsItem>()
    val eventDetail = Transformations.map(_eventDetail) { it }

    private val _checkinResponse = MutableLiveData<CheckinResponse>()
    val checkinResponse = Transformations.map(_checkinResponse) { it }

    fun getEventDetail(eventId: String?) {

        viewModelScope.launch {

            // if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            val eventDetailResponse = eventsRepository.getEventDetail(eventId)

            withContext(Dispatchers.Main) {
                when (eventDetailResponse) {
                    is Result.Success -> {
                        // errorVisibility.set(GONE)
                        _eventDetail.postValue(eventDetailResponse.data?.toEventsItem())
                    }
                    is Result.Error -> {
                        // errorVisibility.set(VISIBLE)
                        // errorMessage.value = R.string.server_error
                    }
                }
            }

            // setProgressBarVisibility(false)
        }

    }


    fun makeCheckin(name: String, email: String, eventId: String?) {
/*        when (val response =
            eventsRepository.makeCheckin(CheckinRequestBody(name, email, eventId))) {
            is NetworkResponse.Success -> {
                _checkinResponse.postValue(response.body)
            }
            is NetworkResponse.ServerError -> {
                setServerError(true)
            }
            else -> {
                setUnknownError(true)
            }
        }*/
    }
}
