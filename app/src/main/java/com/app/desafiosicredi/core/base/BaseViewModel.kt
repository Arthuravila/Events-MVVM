package com.app.desafiosicredi.core.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.desafiosicredi.core.utils.helpers.Event
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    private val _unknownError = MutableLiveData<Event<Boolean>>()
    val unknownError = Transformations.map(_unknownError) { it }

    private val _serverError = MutableLiveData<Event<Boolean>>()
    val serverError = Transformations.map(_serverError) { it }

    private val _progressBarVisibility = MutableLiveData(true)
    val progressBarVisibility = Transformations.map(_progressBarVisibility) { it }

    private val viewModelExceptionHandler =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.d(
                ">>>CoroutineExcpHndlr",
                "coroutineContext: $coroutineContext throwable: ${throwable.printStackTrace()}"
            )
        }

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO + viewModelExceptionHandler

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }

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