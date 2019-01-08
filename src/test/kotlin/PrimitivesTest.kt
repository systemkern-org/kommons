package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal
import java.math.RoundingMode.HALF_EVEN

internal class PrimitivesTest {

    @Test fun `Can get BigDecimal from Double`() {
        assertThat(10.0.bd).isEqualTo(BigDecimal.TEN.setScale(1))
    }

    @Test fun `Can get Float from Double`() {
        assertThat(10.000.f).isEqualTo(10.000F)
        assertThat(10.499.f).isEqualTo(10.499F)
    }

    @Test fun `Can get Integer from Double`() {
        assertThat(10.000.i).isEqualTo(10)
        assertThat(10.1.i).isEqualTo(10)
        assertThat(10.9.i).isEqualTo(10)
    }

    @Test fun `Can get Long from Double`() {
        assertThat(10.000.l).isEqualTo(10L)
    }

    @Test fun `Can get BigDecimal from Float`() {
        assertThat(10.0000f.bd).isEqualTo(BigDecimal.TEN.setScale(1))
        assertThat(10.9999f.bd).isEqualTo(BigDecimal(10.9999).setScale(4, HALF_EVEN))
    }

    @Test fun `Can get Double from Float`() {
        assertThat(10f.d).isEqualTo(10.000)
    }

    @Test fun `Can get Integer from Float`() {
        assertThat(10f.i).isEqualTo(10)
        assertThat(10.1f.i).isEqualTo(10)
        assertThat(10.9f.i).isEqualTo(10)
    }

    @Test fun `Can get Long from Float`() {
        assertThat(10f.l).isEqualTo(10L)
        assertThat(10.1f.l).isEqualTo(10L)
        assertThat(10.9f.l).isEqualTo(10L)
    }

    @Test fun `Can get BigDecimal from Integer`() {
        assertThat(10.bd).isEqualTo(BigDecimal.TEN)
    }

    @Test fun `Can get Double from Integer`() {
        assertThat(10.d).isEqualTo(10.000)
    }

    @Test fun `Can get Float from Integer`() {
        assertThat(10.f).isEqualTo(10.000f)
    }

    @Test fun `Can get Long from Integer`() {
        assertThat(10.l).isEqualTo(10L)
    }

    @Test fun `Can get BigDecimal from Long`() {
        assertThat(10L.bd).isEqualTo(BigDecimal.TEN)
    }

    @Test fun `Can get Double from Long`() {
        assertThat(10L.d).isEqualTo(10.000)
    }

    @Test fun `Can get Float from Long`() {
        assertThat(10L.f).isEqualTo(10.000f)
    }

    @Test fun `Can get Integer from Long`() {
        assertThat(10L.i).isEqualTo(10L)
    }
}
