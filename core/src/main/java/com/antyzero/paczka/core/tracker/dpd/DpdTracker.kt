package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryProvider
import com.antyzero.paczka.core.tracker.HistoryReader
import com.antyzero.paczka.core.tracker.Tracker

class DpdTracker(
    private val historyProvider: HistoryProvider = DpdHistoryProvider,
    private val historyReader: HistoryReader = DpdHistoryReader
) : Tracker {

    override suspend fun parcel(parcelId: Any): List<Step> {
        return historyReader.steps(historyProvider.get(parcelId))
    }
}