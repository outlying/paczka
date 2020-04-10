package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.History

interface Tracker {

    suspend fun parcelHistory(parcelId: Any): History
}