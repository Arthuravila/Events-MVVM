package com.app.desafiosicredi.common.data.api.model

import com.google.gson.annotations.SerializedName

data class PeopleResponse(
    @SerializedName("eventId")
    val eventId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("picture")
    val picture: String?
)

