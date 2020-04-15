package com.antyzero.paczka.core.tracker.ups

import com.antyzero.paczka.core.model.History
import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object UpsHistoryReader : HistoryReader {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val dateFormatter = DateTimeFormatter.ofPattern(
        "MM/dd/yyyy"
    )

    private val timeFormatter = DateTimeFormatter.ofPattern(
        "h:mm a"
    )

    override suspend fun history(input: String): History {
        val result = withContext(Dispatchers.IO) {
            moshi.adapter(Data::class.java).fromJson(input)
                ?: throw IllegalStateException("Unable to deserialize input")
        }

        val trackDetails = result.trackDetails.firstOrNull()
            ?: throw IllegalStateException("Track details not found")

        val steps = trackDetails
            .shipmentProgressActivities
            .map {
                // AM/PM represented as A.M. / P.M. is not recognizable
                val time = it.time.replace(".", "")
                val localDate = LocalDate.parse(it.date, dateFormatter)
                val localTime = LocalTime.parse(time, timeFormatter)
                val localDateTime = LocalDateTime.of(localDate, localTime)
                Step(localDateTime)
            }

        return History(steps)
    }

    /**
     * Main data container
     */
    private data class Data(
        val trackDetails: List<TrackDetail> = emptyList()
    )

    private data class TrackDetail(
        val shipmentProgressActivities: List<ShipmentProgressActivity> = emptyList()
    )

    private data class ShipmentProgressActivity(
        val date: String,
        val time: String,
        val location: String
    )
}