package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.model.Parcel
import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.LocalTime
import org.threeten.bp.format.DateTimeFormatter
import kotlin.text.RegexOption.DOT_MATCHES_ALL
import kotlin.text.RegexOption.IGNORE_CASE

object DpdHistoryReader : HistoryReader {

    private val REGEX_TBODY = "<tbody>.+?</tbody>".toRegex(
        setOf(IGNORE_CASE, DOT_MATCHES_ALL)
    )

    private val REGEX_TR = ("<tr>\\s+?" +
            "<td>(.+?)</td>\\s+?" +
            "<td>(.+?)</td>\\s+?" +
            "<td>(.+?)</td>\\s+?" +
            "<td>(.*?)</td>\\s+?" +
            "</tr>").toRegex(
        setOf(IGNORE_CASE, DOT_MATCHES_ALL)
    )

    override suspend fun history(input: String): Parcel {

        val tbody = REGEX_TBODY
            .findAll(input)
            .first()
            .let { it.groupValues[0] }

        val steps = REGEX_TR
            .findAll(tbody)
            .map { it.groupValues.subList(1, 5) }
            .map {
                val localDateTime = createLocalDateTime(it[0], it[1])
                Step(localDateTime)
            }
            .toList()

        return Parcel(steps)
    }

    private fun createLocalDateTime(date: String, time: String): LocalDateTime {
        val localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
        val localTime = LocalTime.parse(time)
        return LocalDateTime.of(localDate, localTime)
    }
}