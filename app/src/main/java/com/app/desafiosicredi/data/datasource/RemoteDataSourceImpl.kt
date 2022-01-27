package com.app.desafiosicredi.data.datasource

import com.app.desafiosicredi.data.api.EventsApiService
import com.app.desafiosicredi.data.executeApi
import com.app.desafiosicredi.domain.mapper.EventsMapper

class RemoteDataSourceImpl(
    private val apiService: EventsApiService
) : RemoteDataSource {

    override suspend fun getEvents() = executeApi { apiService.getEvents() }

}