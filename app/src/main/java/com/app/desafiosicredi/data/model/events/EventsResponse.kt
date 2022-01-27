package com.app.desafiosicredi.data.model.events

import com.google.gson.annotations.SerializedName

data class EventsResponse(
    @SerializedName("items") val items: ArrayList<EventsItemResponse>
)