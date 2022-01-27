package com.app.desafiosicredi.domain.model.events

import android.os.Parcelable
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Events(
    val items: ArrayList<EventsItem>
) : Parcelable