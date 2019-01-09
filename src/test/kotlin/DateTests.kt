package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.LocalDate

private class DateTests {

    @Test fun `Can get  LocalDate to ISO String`() {
        assertThat(LocalDate.of(2000, 2, 29).isoStr).isEqualTo("2000-02-29")
    }
}
