package com.systemkern.kommons

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE
import java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME

/** @return the ISO 8601 yyyy-mm-dd string representation of this date */
fun LocalDate.format(format: String = ""): String =
    (if (format.isEmpty()) ISO_LOCAL_DATE else DateTimeFormatter.ofPattern(format))
        .let { this.format(it) }

/** @return the ISO 8601 yyyy-mm-dd string representation of this date */
fun LocalDateTime.format(format: String = ""): String =
    (if (format.isEmpty()) ISO_LOCAL_DATE_TIME else DateTimeFormatter.ofPattern(format))
        .let { this.format(it) }

fun String.toLocalDate(format: String = ""): LocalDate =
    (if (format.isEmpty()) ISO_LOCAL_DATE else DateTimeFormatter.ofPattern(format))
        .let { LocalDate.parse(this, it) }

/**
 * Convert the given string to a LocalDateTimeObject.
 * Accepts both dd-MM-yyyy and DateTimeFormatter.ISO_LOCAL_DATE_TIME string formats
 * @throws Exception if "parse" throws an exception
 */
fun String.toLocalDateTime(format: String = ""): LocalDateTime =
    try {
        (if (format.isEmpty()) ISO_LOCAL_DATE_TIME else DateTimeFormatter.ofPattern(format))
            .let { LocalDateTime.parse(this, it) }
    } catch (e: Exception) {
        if (format.isNotEmpty() && format.length != 10 /* ISO_LOCAL_DATE lenght*/)
            throw e
        // Fallback to LocalDate parsing
        try {
            this.toLocalDate(format).atStartOfDay()
        } catch (e2: Exception) {
            e.addSuppressed(e2)
            throw e
        }
    }
