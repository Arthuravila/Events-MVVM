package com.app.desafiosicredi.domain.model.events

import android.os.Parcelable
import com.app.desafiosicredi.data.model.events.EventsItemResponse
import kotlinx.android.parcel.Parcelize

@Parcelize
class Events() : ArrayList<EventsItem>(), Parcelable {

    constructor(items: ArrayList<EventsItem>) : this() {
        this.addAll(items)
    }
}