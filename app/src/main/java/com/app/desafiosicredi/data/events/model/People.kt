package com.app.desafiosicredi.data.events.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    @SerializedName("eventId")
    val eventId: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("picture")
    val picture: String?
): Parcelable

