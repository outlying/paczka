package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.History

interface HistoryReader {

    suspend fun history(input: String): History
}