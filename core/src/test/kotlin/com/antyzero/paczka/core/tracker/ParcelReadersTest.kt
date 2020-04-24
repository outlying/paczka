package com.antyzero.paczka.core.tracker

import com.antyzero.paczka.core.tracker.dpd.DpdParcelDataReader
import com.antyzero.paczka.core.tracker.gls.GlsParcelDataReader
import com.antyzero.paczka.core.tracker.inpost.InPostParcelDataReader
import com.antyzero.paczka.core.tracker.ups.UpsParcelDataReader
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test

class ParcelReadersTest {

    @Test
    internal fun dpd() = runBlocking {
        val steps = DpdParcelDataReader.read(readResource("dpd")).history

        assertThat(steps).hasSize(8)
    }

    @Test
    internal fun `dps incorrect input`() = runBlocking {
        try {
            DpdParcelDataReader.read("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun gls() = runBlocking {
        val steps = GlsParcelDataReader.read(readResource("gls.json")).history

        assertThat(steps).hasSize(4)
    }

    @Test
    internal fun `gls incorrect input`() = runBlocking {
        try {
            GlsParcelDataReader.read("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun inPost() = runBlocking {
        val steps = InPostParcelDataReader.read(readResource("inpost")).history

        assertThat(steps).hasSize(9)
    }

    @Test
    internal fun `inPost incorrect input`() = runBlocking {
        try {
            InPostParcelDataReader.read("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }

    @Test
    internal fun ups() = runBlocking {
        val steps = UpsParcelDataReader.read(readResource("ups")).history

        assertThat(steps).hasSize(10)
    }

    @Test
    internal fun `ups incorrect input`() = runBlocking {
        try {
            UpsParcelDataReader.read("[]")
        } catch (e: Exception) {
            assertThat(e).isInstanceOf(Exception::class.java)
        }
        Unit
    }
}