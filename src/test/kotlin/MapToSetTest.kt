package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private class MapToSetTest {

    @Test fun `Can map to Set`() {
        val a = listOf(1, 2, null, 3, 3, null)

        val set = a.toList().mapToSet { it }

        assertThat(set).containsAll(a.toSet())
    }

    @Test fun `Can map to Set not null`() {
        val a = listOf(1, 2, null, 3, 3)

        val set = a.toList().mapNotNullToSet { it }

        assertThat(set).containsAll(a.filterNot { it == null })
    }
}
