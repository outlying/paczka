package com.antyzero.paczka.core.tracker.inpost

import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object InPostHistoryReader : HistoryReader {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val dateFormatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'+'x"
    )

    override suspend fun steps(input: String): List<Step> {
        val result = moshi.adapter(Data::class.java).fromJson(input)
            ?: throw IllegalStateException("Unable to deserialize input")

        return result
            .tracking_details
            .map {
                val localDateTime = LocalDateTime.parse(
                    it.datetime,
                    DateTimeFormatter.ISO_DATE_TIME
                )
                Step(localDateTime)
            }
    }

    private data class Data(val tracking_details: List<Item>)

    private data class Item(
        val status: String,
        val origin_status: String,
        val agency: String?,
        val datetime: String
    )
}