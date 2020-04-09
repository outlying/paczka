package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.tracker.dpd.DpdHistoryReader
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class HistoryReadersTest {

    @Test
    internal fun dpd() = runBlocking {
        val steps = DpdHistoryReader().steps(readResource("dpd"))

        assertThat(steps).hasSize(8)
    }
}