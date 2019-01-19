package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.reflect.full.starProjectedType


internal class EnumerationsTests {

    @Test fun `Can determine lower case enums first value`() {
        val ret = LowerCase::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNotNull
        assertThat(ret.toString()).isEqualTo("value1")
    }

    @Test fun `Can determine upper case enums first value`() {
        val ret = UpperCase::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNotNull
        assertThat(ret.toString()).isEqualTo("VALUE1")
    }

    @Test fun `Can determine camel case enums first value`() {
        val ret = CamelCase::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNotNull
        assertThat(ret.toString()).isEqualTo("SomeValue")
    }

    @Test fun `Can determine custom value enums first value`() {
        val ret = CustomValueString::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNotNull
        assertThat(ret.toString()).isEqualTo("VALUE1")
    }

    @Test fun `Can determine underscore enums first value`() {
        val ret = Underscore::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNotNull
        assertThat(ret.toString()).isEqualTo("Some_Value")
    }

    @Test fun `Can get null for non enum type`() {
        val ret = Any::class.starProjectedType.firstEnumValueOrNull
        assertThat(ret).isNull()
    }

}

private enum class LowerCase { value1 }

private enum class UpperCase { VALUE1 }

private enum class CamelCase { SomeValue }

private enum class Underscore { Some_Value }


private enum class CustomValueString(
    private val stringValue: String
) {
    VALUE1("This.Is_actually!The#Value")
}
