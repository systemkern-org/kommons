package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class StandardLambdaTests {

    @Test fun `Can execute alsoIfTrue-lambda if condition is true`() {
        val orig = Any()
        var executed = false
        val res = orig.alsoIfTrue(true) { executed = true }

        assertThat(res).isEqualTo(orig)
        assertThat(executed).isEqualTo(true)
    }

    @Test fun `Cannot execute alsoIfTrue-lambda if condition is false`() {
        val orig = Any()
        var executed = false
        val res = orig.alsoIfTrue(false) { executed = true }

        assertThat(res).isEqualTo(orig)
        assertThat(executed).isEqualTo(false)
    }

    @Test fun `Can execute alsoIfFalse-lambda if condition is true`() {
        val orig = Any()
        var executed = false
        val res = orig.alsoIfFalse(false) { executed = true }

        assertThat(res).isEqualTo(orig)
        assertThat(executed).isEqualTo(true)
    }

    @Test fun `Cannot execute alsoIfFalse-lambda if condition is false`() {
        val orig = Any()
        var executed = false
        val res = orig.alsoIfFalse(true) { executed = true }

        assertThat(res).isEqualTo(orig)
        assertThat(executed).isEqualTo(false)
    }

    @Test fun `Can execute letIfTrue-lambda if condition is true`() {
        val second = Any()
        val res = Any().letIfTrue(true) { second }

        assertThat(res).isEqualTo(second)
    }

    @Test fun `Cannot execute letIfTrue-lambda if condition is false`() {
        val res = Any().letIfTrue(false) { Any() }

        assertThat(res).isNull()
    }

    @Test fun `Can execute letIfFalse-lambda if condition is true`() {
        val second = Any()
        val res = Any().letIfFalse(false) { second }

        assertThat(res).isEqualTo(second)
    }

    @Test fun `Cannot execute letIfFalse-lambda if condition is false`() {
        val res = Any().letIfFalse(true) { Any() }

        assertThat(res).isNull()
    }
}
