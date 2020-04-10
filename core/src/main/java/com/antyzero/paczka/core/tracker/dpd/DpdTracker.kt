package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.model.History
import com.antyzero.paczka.core.tracker.HistoryProvider
import com.antyzero.paczka.core.tracker.HistoryReader
import com.antyzero.paczka.core.tracker.Tracker

class DpdTracker(
    private val historyProvider: HistoryProvider = DpdHistoryProvider,
    private val historyReader: HistoryReader = DpdHistoryReader
) : Tracker {

    override suspend fun parcelHistory(parcelId: Any): History {
        return historyReader.history(historyProvider.get(parcelId))
    }
}