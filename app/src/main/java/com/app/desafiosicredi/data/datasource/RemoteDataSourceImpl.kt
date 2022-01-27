package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.data.api.EventsApiService
import com.app.desafiosicredi.data.mapper.EventsMapper

class RemoteDataSourceImpl(
    private val api: EventsApiService
) : RemoteDataSource {

    private val mapper: EventsMapper = EventsMapper()

    override suspend fun getEvents() = mapper.map(api.getEvents())
}
