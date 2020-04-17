package com.antyzero.paczka.core.tracker.inpost

import com.antyzero.paczka.core.model.History
import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

object InPostHistoryReader : HistoryReader {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val dateFormatter = DateTimeFormatter.ofPattern(
        "yyyy-MM-dd'T'HH:mm:ss.SSS'+'x"
    )

    override suspend fun history(input: String): History {
        val result = withContext(Dispatchers.IO) {
            try {
                requireNotNull(moshi.adapter(Data::class.java).fromJson(input))
            } catch (e: Exception) {
                throw IllegalStateException("Unable to deserialize $input", e)
            }
        }

        val steps = result
            .tracking_details
            .map {
                val localDateTime = LocalDateTime.parse(
                    it.datetime,
                    DateTimeFormatter.ISO_DATE_TIME
                )
                Step(localDateTime)
            }

        return History(steps)
    }

    private data class Data(val tracking_details: List<Item>)

    private data class Item(
        // val status: String,
        // val origin_status: String,
        // val agency: String?,
        val datetime: String
    )
}