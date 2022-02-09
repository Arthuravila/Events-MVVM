package com.app.desafiosicredi.common.domain.model.events

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class People(
    val eventId: String?,
    val id: String?,
    val name: String?,
    val picture: String?
) : Parcelable