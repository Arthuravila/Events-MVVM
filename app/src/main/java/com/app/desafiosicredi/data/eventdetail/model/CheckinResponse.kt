package com.app.desafiosicredi.data.eventdetail.model


import com.google.gson.annotations.SerializedName

data class CheckinResponse(
    @SerializedName("code")
    val code: String?
)