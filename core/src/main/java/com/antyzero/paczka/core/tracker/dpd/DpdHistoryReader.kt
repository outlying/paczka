package com.antyzero.paczka.core.tracker.dpd

import com.antyzero.paczka.core.model.Step
import com.antyzero.paczka.core.tracker.HistoryReader
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

    override suspend fun steps(input: String): List<Step> {

        val tbody = REGEX_TBODY
            .findAll(input)
            .first()
            .let { it.groupValues[0] }

        return REGEX_TR
            .findAll(tbody)
            .map { it.groupValues }
            .map { Step() }
            .toList()
    }
}