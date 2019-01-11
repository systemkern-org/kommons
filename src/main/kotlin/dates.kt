package com.systemkern.kommons

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/** @return the ISO 8601 yyyy-mm-dd string representation of this date */
fun LocalDate.format(format: String = "") : String {
    val dateFormatter = if (format.isEmpty()) DateTimeFormatter.ISO_LOCAL_DATE else DateTimeFormatter.ofPattern(format)
    return this.format(dateFormatter)
}

/** @return the ISO 8601 yyyy-mm-dd string representation of this date */
fun LocalDateTime.format(format: String = ""): String {
    val dateFormatter = if (format.isEmpty()) DateTimeFormatter.ISO_LOCAL_DATE_TIME else DateTimeFormatter.ofPattern(format)
    return this.format(dateFormatter)
}

fun String.toLocalDate(format: String = ""): LocalDate? =
    try {
        val dateFormatter = if (format.isEmpty()) DateTimeFormatter.ISO_LOCAL_DATE else DateTimeFormatter.ofPattern(format)
        LocalDate.parse(this, dateFormatter)
    } catch (e: Exception) {
        null
    }

fun String.toLocalDateTime(format: String = ""): LocalDateTime? {
    return try {
        val dateTimeFormatter = if (format.isEmpty()) DateTimeFormatter.ISO_LOCAL_DATE_TIME else DateTimeFormatter.ofPattern(format)
        // first try to format as datetime
        LocalDateTime.parse(this, dateTimeFormatter)
    } catch (e: Exception) {
        // if not try to format as date and convert to datetime on (0,0,0)
        val date = this.toLocalDate(format) ?: return null
        LocalDateTime.of(date, LocalTime.of(0,0,0))
    }
}

