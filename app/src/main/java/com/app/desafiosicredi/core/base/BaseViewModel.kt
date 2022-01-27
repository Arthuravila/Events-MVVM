package com.app.desafiosicredi.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.desafiosicredi.core.utils.helpers.Event

open class BaseViewModel : ViewModel() {

    private val _unknownError = MutableLiveData<Event<Boolean>>()
    val unknownError = Transformations.map(_unknownError) { it }

    private val _serverError = MutableLiveData<Event<Boolean>>()
    val serverError = Transformations.map(_serverError) { it }

    private val _progressBarVisibility = MutableLiveData(true)
    val progressBarVisibility = Transformations.map(_progressBarVisibility) { it }


    fun setProgressBarVisibility(isLoading: Boolean) {
        _progressBarVisibility.postValue(isLoading)
    }

    fun setServerError(serverError: Boolean) {
        _serverError.postValue(Event(serverError))
    }

    fun setUnknownError(unknownError: Boolean) {
        _unknownError.postValue(Event(unknownError))
    }
}
