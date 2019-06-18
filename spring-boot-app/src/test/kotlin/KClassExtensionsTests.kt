package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

internal class KClassExtensionsTest {

    @Test fun `Can get class properties`() {
        val ret = MyDataClass::class.entityProperties

        with(ret.map { it.name }) {
            assertThat(this).contains("version", "str", "obj")
            assertThat(this).doesNotContain("id")
        }
    }

}

private data class MyDataClass(
    private val id: Long,
    private val version: Long = 0L,
    private val str: String,
    private val obj: MyDataClass,
    private val col: SortedSet<MyDataClass> = sortedSetOf()
)
