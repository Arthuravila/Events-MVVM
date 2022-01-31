package com.app.desafiosicredi.data.model.checkin


import com.google.gson.annotations.SerializedName

data class CheckinResponse(
    @SerializedName("code")
    val code: String?
)