package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.tracker.readResource
import com.antyzero.paczka.core.tracker.url
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.Test


internal class DpdParcelDataProviderTest {

    @Test
    fun success() {
        val parcelData = readResource("dpd")
        val server = MockWebServer()
        server.enqueue(MockResponse().setBody(parcelData))
        server.start()

        val response = runBlocking {
            DpdParcelDataProvider(OkHttpClient(), server.url()).get("")
        }

        assertThat(response).isEqualTo(parcelData)
    }

    @Test
    fun `invalid parcel number`() {

        val response = runBlocking {
            DpdParcelDataProvider(OkHttpClient()).get("")
        }

        assertThat(response).contains("Wprowadzono błędny numer przesyłki")
    }
}