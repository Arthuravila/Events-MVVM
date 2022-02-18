package com.app.desafiosicredi.common.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.app.desafiosicredi.common.domain.actions.Action
import com.app.desafiosicredi.common.presentation.ViewState
import com.app.desafiosicredi.common.data.api.Result

abstract class BaseViewModel<S: ViewState, A: Action, R: Result> : ViewModel() {
    protected abstract val internalViewState: S

    protected abstract fun handle(action: A): LiveData<R>
    protected abstract fun reduce(result: R): S

    private val nextAction = MutableLiveData<A>()

    val viewState = Transformations.map(Transformations.switchMap(nextAction) {
        handle(it)
    }) {
        reduce(it)
    }

    fun dispatch(action: A) {
        nextAction.value = action
    }

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
