package com.antyzero.paczka.core.tracker.gls

import com.antyzero.paczka.core.model.Parcel
import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object GlsHistoryReader : HistoryReader {

    private val moshi = Moshi.Builder()
        .add(JsonStepAdapter)
        .add(KotlinJsonAdapterFactory())
        .build()

    override suspend fun history(input: String): Parcel {
        val result = withContext(Dispatchers.IO) {
            try {
                requireNotNull(moshi.adapter(Data::class.java).fromJson(input))
            } catch (e: Exception) {
                throw IllegalStateException("Unable to deserialize $input", e)
            }
        }

        return Parcel(history = result.tuStatus.first().history)
    }

    private data class Data(
        val tuStatus: List<Package>
    )

    private data class Package(
        val history: List<Step>
    )

    private data class JsonHistory(
        val date: String,
        val time: String
    )

    private object JsonStepAdapter {

        private val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

        @FromJson
        fun fromJson(jsonHistory: JsonHistory): Step {
            val time = LocalTime.parse(jsonHistory.time, timeFormatter)
            val date = LocalDate.parse(jsonHistory.date, dateFormatter)

            return Step(
                dateTime = LocalDateTime.of(date, time)
            )
        }
    }
}