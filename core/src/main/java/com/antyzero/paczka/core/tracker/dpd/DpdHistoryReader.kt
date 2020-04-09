package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader

class DpdHistoryReader : HistoryReader {

    override suspend fun steps(input: String): List<Step> {
        return emptyList()
    }
}