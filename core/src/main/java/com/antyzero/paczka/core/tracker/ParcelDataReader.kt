package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Parcel

/**
 * Implementations are responsible for translating raw package data into an internal format of [Parcel]
 */
interface ParcelDataReader {

    /**
     * Reads raw input [String] and converts into [Parcel] data
     *
     * @param [input] anything used to represent parcel in external system (raw: HTML, JSON, XML etc)
     */
    suspend fun read(input: String): Parcel
}