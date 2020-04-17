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
    internal fun `dps incorrect input`() = runBlocking {
        try {
            DpdHistoryReader.history("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun gls() = runBlocking {
        val steps = GlsHistoryReader.history(readResource("gls.json")).steps

        assertThat(steps).hasSize(4)
    }

    @Test
    internal fun `gls incorrect input`() = runBlocking {
        try {
            GlsHistoryReader.history("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun inPost() = runBlocking {
        val steps = InPostHistoryReader.history(readResource("inpost")).steps

        assertThat(steps).hasSize(9)
    }

    @Test
    internal fun `inPost incorrect input`() = runBlocking {
        try {
            InPostHistoryReader.history("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun ups() = runBlocking {
        val steps = UpsHistoryReader.history(readResource("ups")).steps

        assertThat(steps).hasSize(10)
    }

    @Test
    internal fun `ups incorrect input`() = runBlocking {
        try {
            UpsHistoryReader.history("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }
}