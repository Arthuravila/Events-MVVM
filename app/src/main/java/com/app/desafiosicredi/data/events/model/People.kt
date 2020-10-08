package com.app.desafiosicredi.data.events.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    @SerializedName("picture") val picture: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("eventId") val eventId: Int?,
    @SerializedName("id") val id: Int?
): Parcelable
