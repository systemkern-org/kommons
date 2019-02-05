package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class TupleTests {

    @Test fun `Can create inline triple`() {
        val res = 1 to "2" to true
        with(res) {
            assertThat(first).isEqualTo(1)
            assertThat(second).isEqualTo("2")
            assertThat(third).isEqualTo(true)
        }
    }

    @Test fun `Can create inline triple2`() {
        val res = 1 to ("2" to true)
        with(res) {
            assertThat(first).isEqualTo(1)
            assertThat(second).isEqualTo("2")
            assertThat(third).isEqualTo(true)
        }
    }
}
