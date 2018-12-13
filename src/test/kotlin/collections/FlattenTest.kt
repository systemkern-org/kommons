package com.systemkern.kommons.collections

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private class FlattenTest {

    @Test fun `Can flatten multiple lists`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)

        val joinedList = flatten(a, b)

        assertThat(joinedList).containsAll(a)
        assertThat(joinedList).containsAll(b)
        assertThat(joinedList).containsOnly(*listOf(a, b).flatten().toTypedArray())
    }
}
