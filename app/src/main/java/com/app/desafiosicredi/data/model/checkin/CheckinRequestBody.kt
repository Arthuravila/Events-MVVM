package com.app.desafiosicredi.data.model.checkin

import com.google.gson.annotations.SerializedName

data class CheckinRequestBody(
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("email")
    var email: String? = "",
    @SerializedName("eventId")
    var eventId: String? = ""
)