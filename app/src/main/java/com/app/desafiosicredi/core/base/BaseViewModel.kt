package com.app.desafiosicredi.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


open class BaseViewModel() : ViewModel() {

    data class ErrorState(
        val errorVisibility: Boolean = false,
        val errorMessage: String? = ""
    )

    private val _progressBarVisibility = MutableLiveData(true)
    val progressBarVisibility: LiveData<Boolean>
        get() = _progressBarVisibility

    private val _errorState = MutableLiveData<ErrorState>()
    val errorState: LiveData<ErrorState>
        get() = _errorState


    fun setProgressBarVisibility(isLoading: Boolean) {
        _progressBarVisibility.postValue(isLoading)
    }

    fun setErrorState(errorVisibility: Boolean, errorMessage: String? = null) {
        _errorState.postValue(ErrorState(errorVisibility, errorMessage))
    }


}
