package com.app.desafiosicredi.events.presentation.events

import com.app.desafiosicredi.common.domain.model.events.Events
import com.app.desafiosicredi.common.presentation.Event
import com.app.desafiosicredi.common.presentation.ViewState

data class EventsViewState(
    val isLoading: Boolean = false,
    val events: Events? = null,
    val error: Event<Throwable>? = null
) : ViewState
