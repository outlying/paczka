package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.tracker.HistoryProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request

/*
object DpdHistoryProvider : HistoryProvider {

    private val okHttpClient = OkHttpClient.Builder().build()
    private val trackingUrl = "https://tracktrace.dpd.com.pl/findPackage".toHttpUrlOrNull()
        ?: throw IllegalStateException("Unable to parse base URL")

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
            okHttpClient.newCall(request).execute().message
        }
    }
}
 */