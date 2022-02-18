package com.app.desafiosicredi.common.domain.actions

sealed class EventsAction : Action {
    object GetEventsAction : EventsAction()
}
