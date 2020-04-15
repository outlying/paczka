package com.antyzero.paczka.core.model

import org.threeten.bp.LocalDateTime

/**
 * Representing single step in parcel history
 *
 * Notes:
 *
 * DPD - date, hour, description, department
 * InPost - status, origin_status, agency, datetime
 */
data class Step(
    val dateTime: LocalDateTime
)