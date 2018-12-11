package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

private class DateRangeTest {

    @Test fun `Contain contains correct LocalDates`() {
        val dates = (0..42).map {
            LocalDate.now().plusDays(it.l)
        }

        val range: DateRange = (dates.first()..dates.last())

        assertThat(range).containsAll(dates)
        assertThat(range).containsOnly(*dates.toTypedArray())
    }

    @Test fun `Contains correct LocalDateTimes`() {
        val dates = (0..42).map {
            LocalDateTime.now().plusDays(it.l)
        }

        val range = (dates.first()..dates.last())

        assertThat(range.containsAll(dates)).isTrue()
    }

    @Test fun `Contains LocalDates in correct order`() {
        val dates = (0..42).map {
            LocalDate.now().plusDays(it.l)
        }

        val range = (dates.first()..dates.last())

        assertThat(range).containsExactly(*dates.toTypedArray())
    }

    @Test fun `Can Create LocalDate to LocalDateTime DateRange`() {
        assertThat(LocalDate.now()..LocalDateTime.now()).containsExactly(LocalDate.now())
    }

    @Test fun `Can Create LocalDateTime to LocalDate DateRange`() {
        assertThat(LocalDateTime.now()..LocalDate.now()).containsExactly(LocalDate.now())
    }

    @Test fun `Can Create LocalDateTime to LocalDateTime DateRange`() {
        assertThat(LocalDateTime.now()..LocalDateTime.now()).containsExactly(LocalDate.now())
    }
}
