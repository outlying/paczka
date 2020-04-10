package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Step

interface Tracker {

    suspend fun parcel(parcelId: Any): List<Step>
}