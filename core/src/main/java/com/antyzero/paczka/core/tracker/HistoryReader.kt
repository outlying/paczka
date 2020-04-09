package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.model.Step

interface HistoryReader {

    fun steps(input: String): List<Step>
}