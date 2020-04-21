package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Parcel

interface HistoryReader {

    suspend fun history(input: String): Parcel
}