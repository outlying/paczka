package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Parcel

interface Tracker {

    suspend fun parcelHistory(parcelId: Any): Parcel
}