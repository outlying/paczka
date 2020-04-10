package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.tracker.dpd.DpdHistoryReader
import com.antyzero.paczka.core.tracker.inpost.InPostHistoryReader
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class HistoryReadersTest {

    @Test
    internal fun dpd() = runBlocking {
        val steps = DpdHistoryReader.history(readResource("dpd")).steps

        assertThat(steps).hasSize(8)
    }

    @Test
    internal fun inPost() = runBlocking {
        val steps = InPostHistoryReader.history(readResource("inpost")).steps

        assertThat(steps).hasSize(9)
    }
}