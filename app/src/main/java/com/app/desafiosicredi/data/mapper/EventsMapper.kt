package com.app.desafiosicredi.data.mapper

import com.app.desafiosicredi.core.utils.Mapper
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import com.app.desafiosicredi.data.model.events.EventsResponse
import com.app.desafiosicredi.data.model.events.PeopleResponse
import com.app.desafiosicredi.domain.model.events.Events
import com.app.desafiosicredi.domain.model.events.EventsItem
import com.app.desafiosicredi.domain.model.events.People

class EventsMapper: Mapper<EventsResponse, Events> {

    override fun map(source: EventsResponse): Events {
        val items = source.items.map { event -> event.toEventsItem() }
        return Events(items.toCollection(ArrayList()))
    }
}

fun EventsItemResponse.toEventsItem() = EventsItem(
    date = date,
    description = description,
    id = id,
    image = image,
    latitude = latitude,
    longitude = longitude,
    people = people?.map { it.toPeople() },
    price = price,
    title = title
)

fun PeopleResponse.toPeople() = People(
    eventId = eventId,
    id = id,
    name = name,
    picture = picture
)