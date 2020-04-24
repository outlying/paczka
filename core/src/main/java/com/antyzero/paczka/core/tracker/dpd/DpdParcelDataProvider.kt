package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.tracker.ParcelDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

class DpdParcelDataProvider(
    private val okHttpClient: OkHttpClient = OkHttpClient.Builder().build(),
    private val trackingUrl: HttpUrl = DEFAULT_TRACKING_URL
) : ParcelDataProvider {

    override suspend fun get(id: Any): String {

        val requestBody = FormBody.Builder()
            .add("q", id.toString())
            .add("typ", "1")
            .build()

        val request: Request = Request.Builder()
            .url(trackingUrl)
            .post(requestBody)
            .build()

        return withContext(Dispatchers.IO) {
            okHttpClient.newCall(request).execute().body?.string()
                ?: throw IllegalStateException("Unable to read response")
        }
    }

    companion object {

        val DEFAULT_TRACKING_URL = "https://tracktrace.dpd.com.pl/findPackage".toHttpUrlOrNull()
            ?: throw IllegalStateException("Unable to parse default URL")
    }
}