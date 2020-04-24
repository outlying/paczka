package com.antyzero.paczka.core.tracker

/**
 * Gathers information about given parcel ID
 */
interface HistoryProvider {

    /**
     * Gathers parcel information, usually from external system
     *
     * @param [id], anything provided will be converted to [String] by it's [toString] function
     */
    suspend fun get(id: Any): String
}