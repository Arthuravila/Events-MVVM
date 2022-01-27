package com.app.desafiosicredi.data.model.events

import com.google.gson.annotations.SerializedName

data class EventsItemResponse(
    @SerializedName("date")
    val date: Long?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("latitude")
    val latitude: Double?,
    @SerializedName("longitude")
    val longitude: Double?,
    @SerializedName("people")
    val people: List<PeopleResponse>?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
)
