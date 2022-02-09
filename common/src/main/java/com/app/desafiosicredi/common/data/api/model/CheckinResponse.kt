package com.app.desafiosicredi.common.data.api.model


import com.google.gson.annotations.SerializedName

data class CheckinResponse(
    @SerializedName("code")
    val code: String?
)