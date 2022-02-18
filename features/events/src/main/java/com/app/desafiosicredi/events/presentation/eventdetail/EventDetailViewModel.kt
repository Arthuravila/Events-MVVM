package com.app.desafiosicredi.events.presentation.eventdetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.common.base.BaseViewModel
import com.app.desafiosicredi.common.data.api.ApiResult
import com.app.desafiosicredi.common.data.api.model.CheckinRequestBody
import com.app.desafiosicredi.common.data.api.model.CheckinResponse
import com.app.desafiosicredi.common.data.api.model.EventsResponse
import com.app.desafiosicredi.common.data.dispatchers.EventsDispatcher
import com.app.desafiosicredi.common.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.common.domain.actions.EventsAction
import com.app.desafiosicredi.common.domain.mapper.toEventsItem
import com.app.desafiosicredi.common.domain.model.events.EventsItem
import com.app.desafiosicredi.events.presentation.events.EventsViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EventDetailViewModel(private val dispatcher: EventsDispatcher) :
    BaseViewModel<EventsViewState, EventsAction, ApiResult<EventsResponse>>() {

    override val internalViewState = EventsViewState()

    private val _eventDetail = MutableLiveData<EventsItem>()
    val eventDetail: LiveData<EventsItem>
        get() = _eventDetail

    private val _checkinResponse = MutableLiveData<CheckinResponse>()
    val checkinResponse: LiveData<CheckinResponse>
        get() = _checkinResponse

    fun getEventDetail(eventId: String?) {

/*        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            val eventDetailResponse = eventsRepository.getEventDetail(eventId)

            withContext(Dispatchers.Main) {
                when (eventDetailResponse) {
                    is ApiResult.Success -> {
                        setErrorState(false)
                        _eventDetail.postValue(eventDetailResponse.data?.toEventsItem())
                    }
                    is ApiResult.Error -> {
                        setErrorState(true, eventDetailResponse.errorMessage)
                    }
                }
            }

            setProgressBarVisibility(false)
        }*/

    }


    fun makeCheckin(name: String, email: String, eventId: String?) {

/*        viewModelScope.launch {
            val checkinResponse =
                eventsRepository.makeCheckin(CheckinRequestBody(name, email, eventId))

            withContext(Dispatchers.Main) {
                when (checkinResponse) {
                    is ApiResult.Success -> {
                        setErrorState(false)
                        _checkinResponse.postValue(checkinResponse.data)
                    }
                    is ApiResult.Error -> {
                        setErrorState(false, checkinResponse.errorMessage)
                    }
                }
            }
        }*/
    }

    override fun handle(action: EventsAction): LiveData<ApiResult<EventsResponse>> {
        TODO("Not yet implemented")
    }

    override fun reduce(result: ApiResult<EventsResponse>): EventsViewState {
        TODO("Not yet implemented")
    }
}
