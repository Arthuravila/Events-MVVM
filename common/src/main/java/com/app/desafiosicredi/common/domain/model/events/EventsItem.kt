package com.app.desafiosicredi.common.domain.model.events

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventsItem(
    val date: Long?,
    val description: String?,
    val id: String?,
    val image: String?,
    val latitude: Double?,
    val longitude: Double?,
    val people: List<People>?,
    val price: Double?,
    val title: String?
) : Parcelable