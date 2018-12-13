package com.systemkern.kommons.collections

import com.systemkern.kommons.com.systemkern.kommons.collections.distinctListOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private class DistinctListTest {

    @Test fun `Can create list of distinct items`() {
        val l = distinctListOf("A", "B", "C")
        assertThat(l).containsOnly("A", "B", "C")
    }

    @Test fun `Can create list of distinct items 2`() {
        val l = distinctListOf("A", "B", "C", "B")
        assertThat(l).containsExactly("A", "B", "C")
        assertThat(l).size().isEqualTo(3)
    }

}

