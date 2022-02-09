package com.app.desafiosicredi.common.domain.model.events

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Events() : ArrayList<EventsItem>(), Parcelable {

    constructor(items: ArrayList<EventsItem>) : this() {
        this.addAll(items)
    }
}