package com.app.desafiosicredi.data.eventdetail.model

import android.os.Parcelable
import com.app.desafiosicredi.data.events.model.People
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventDetail(
    @SerializedName("date")
    val date: Int?,
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
    val people: List<People>?,
    @SerializedName("price")
    val price: Double?,
    @SerializedName("title")
    val title: String?
): Parcelable