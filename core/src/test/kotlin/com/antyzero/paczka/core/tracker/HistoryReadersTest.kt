package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.tracker.dpd.DpdHistoryReader
import com.antyzero.paczka.core.tracker.gls.GlsHistoryReader
import com.antyzero.paczka.core.tracker.inpost.InPostHistoryReader
import com.antyzero.paczka.core.tracker.ups.UpsHistoryReader
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
    internal fun gls() = runBlocking {
        val steps = GlsHistoryReader.history(readResource("gls.json")).steps

        assertThat(steps).hasSize(4)
    }

    @Test
    internal fun inPost() = runBlocking {
        val steps = InPostHistoryReader.history(readResource("inpost")).steps

        assertThat(steps).hasSize(9)
    }

    @Test
    internal fun ups() = runBlocking {
        val steps = UpsHistoryReader.history(readResource("ups")).steps

        assertThat(steps).hasSize(10)
    }
}