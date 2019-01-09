package com.systemkern.kommons

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import kotlin.reflect.full.starProjectedType

internal class KTypeSimpleNameTests {

    @Test fun `Can determine lower case enums first value`() {
        val ret = GenericFoo::class.starProjectedType.simpleName
        Assertions.assertThat(ret.toString()).isEqualTo("GenericFoo")
    }
    
}

private data class GenericFoo<T> (
    val bar: T
)
