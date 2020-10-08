package com.app.desafiosicredi.data.eventdetail.model

import com.google.gson.annotations.SerializedName

data class CheckinRequestBody(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("eventId")
    var eventId: String? = ""
)