package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private class CollectionExtensionTests {

    @Test fun `Can flatten lists`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)

        with(flatten(a, b)) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
            assertThat(this).containsOnly(*a.union(b).toList().toTypedArray())
        }
        with(flatten(listOf(a, b))) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
            assertThat(this).containsOnly(*a.union(b).toList().toTypedArray())
        }
    }

    @Test fun `Can union two collections`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)

        with(union(a, b)) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
        }
    }

    @Test fun `Can union three collections`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)
        val c = listOf(7, 8, 9)

        with(union(a, b, c)) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
            assertThat(this).containsAll(c)
        }
    }

    @Test fun `Can union four collections`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)
        val c = listOf(7, 8, 9)
        val d = listOf(10, 11, 12)

        with(union(a, b, c, d)) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
            assertThat(this).containsAll(c)
            assertThat(this).containsAll(d)
        }
    }

    @Test fun `Can union five collections`() {
        val a = listOf(1, 2, 3)
        val b = listOf(4, 5, 6)
        val c = listOf(7, 8, 9)
        val d = listOf(10, 11, 12)
        val e = listOf(13, 14, 15)

        with(union(a, b, c, d, e)) {
            assertThat(this).containsAll(a)
            assertThat(this).containsAll(b)
            assertThat(this).containsAll(c)
            assertThat(this).containsAll(d)
            assertThat(this).containsAll(e)
        }
    }


    @Test fun `Can union a collection with some args`() {
        val a = listOf(1, 2, 3)

        with(union(a, 4, 5, 6)) {
            assertThat(this).containsAll(a)
            assertThat(this).contains(4, 5, 6)
        }
    }

    @Test fun `Can map to Set`() {
        val a = listOf(1, 2, null, 3, 3, null)

        val set = a.toList().mapToSet { it }

        assertThat(set).containsAll(a.toSet())
        assertThat(set).containsOnly(*a.toTypedArray())
    }

    @Test fun `Can map to Set not null`() {
        val a = listOf(1, 2, null, 3, 3)

        val set = a.toList().mapNotNullToSet { it }

        assertThat(set).containsAll(a.filterNot { it == null })
        assertThat(set).containsOnly(*a.filterNot { it == null }.toTypedArray())
    }
}
