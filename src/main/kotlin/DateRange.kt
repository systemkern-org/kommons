package com.systemkern.kommons

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit.DAYS

@Suppress("DELEGATED_MEMBER_HIDES_SUPERTYPE_OVERRIDE", "DIFFERENT_NAMES_FOR_THE_SAME_PARAMETER_IN_SUPERTYPES")
class DateRange private constructor(
    override val start: LocalDate,
    override val endInclusive: LocalDate,
    private val list: List<LocalDate>
) : ClosedRange<LocalDate>, List<LocalDate> by list {
    constructor(
        start: LocalDate,
        endInclusive: LocalDate
    ) : this(
        start = start,
        endInclusive = endInclusive,
        list = (0..DAYS.between(start, endInclusive)).map { start.plusDays(it) }
    )

    operator fun contains(other: LocalDateTime) = this.contains(other.toLocalDate())

    fun containsAll(other: Iterable<LocalDateTime>) = other.all { this.contains(it) }
}

operator fun LocalDate.rangeTo(other: LocalDate) = DateRange(this, other)
operator fun LocalDate.rangeTo(other: LocalDateTime) = DateRange(this, other.toLocalDate())
operator fun LocalDateTime.rangeTo(other: LocalDate) = DateRange(this.toLocalDate(), other)
operator fun LocalDateTime.rangeTo(other: LocalDateTime) = DateRange(this.toLocalDate(), other.toLocalDate())
