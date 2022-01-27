package com.app.desafiosicredi.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.app.desafiosicredi.data.Result
import com.app.desafiosicredi.core.base.BaseViewModel
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.data.repository.EventsRepositoryImpl
import com.app.desafiosicredi.domain.mapper.EventsMapper
import kotlinx.coroutines.launch

class EventsViewModel(private val eventsRepository: EventsRepositoryImpl) : BaseViewModel() {

    private val _events = MutableLiveData<Events>()
    val events : LiveData<Events>
        get() = _events

    private val mapper: EventsMapper = EventsMapper()

    fun getEvents()  {

        viewModelScope.launch {

            if (progressBarVisibility.value == false) setProgressBarVisibility(true)

            when (val eventsResponse = eventsRepository.getEvents()) {
                is Result.Success -> {
                    // errorVisibility.set(GONE)
                    _events.postValue(eventsResponse.data?.let { mapper.map(it) })
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
