package com.antyzero.paczka.core.tracker

interface HistoryProvider {

    suspend fun get(id: Any): String
}