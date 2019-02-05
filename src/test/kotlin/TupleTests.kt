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

    @Test fun `Can create and edit a mutable triple`() {
        val triple = MutableTriple(1, "2", false)

        triple.first = 42
        triple.second = "33"
        triple.third = true

        assertThat(triple.first).isEqualTo(42)
        assertThat(triple.second).isEqualTo("33")
        assertThat(triple.third).isTrue()
    }

    @Test fun `Can convert MutableTriple to immutable Triple`() {
        val triple = MutableTriple(1, "2", false)

        with(triple.toTriple()) {
            assertThat(first).isEqualTo(triple.first)
            assertThat(second).isEqualTo(triple.second)
            assertThat(third).isEqualTo(triple.third)
        }
    }
}
