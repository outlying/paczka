package com.antyzero.paczka.core.tracker.ups

import com.antyzero.paczka.core.model.Parcel
import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.ParcelDataReader
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter

object UpsParcelDataReader : ParcelDataReader {

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val dateFormatter = DateTimeFormatter.ofPattern(
        "MM/dd/yyyy"
    )

    private val timeFormatter = DateTimeFormatter.ofPattern(
        "h:mm a"
    )

    override suspend fun read(input: String): Parcel {
        val result = withContext(Dispatchers.IO) {
            try {
                requireNotNull(moshi.adapter(Data::class.java).fromJson(input))
            } catch (e: Exception) {
                throw IllegalStateException("Unable to deserialize input")
            }
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

        return Parcel(steps)
    }

    /**
     * Main data container
     */
    private data class Data(
        val trackDetails: List<TrackDetail>
    )

    private data class TrackDetail(
        val shipmentProgressActivities: List<ShipmentProgressActivity>
    )

    private data class ShipmentProgressActivity(
        val date: String,
        val time: String
        // ,val location: String
    )
}