package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Step

interface HistoryReader {

    suspend fun steps(input: String): List<Step>
}