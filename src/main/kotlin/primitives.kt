package com.systemkern.kommons

import java.math.BigDecimal

val Int.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

val Int.d: Double get() = this.toDouble()

val Int.f: Float get() = this.toFloat()

val Int.l: Long get() = this.toLong()

val Double.f: Float get() = this.toFloat()

val Double.bd: BigDecimal get() = this.toBigDecimal()

val Double.i: Int get() = this.toInt()

val Double.l: Long get() = this.toLong()

val Float.bd: BigDecimal get() = this.toBigDecimal()

val Float.d: Double get() = this.toDouble()

val Float.i: Int get() = this.toInt()

val Float.l: Long get() = this.toLong()

val Long.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

val Long.f: Float get() = this.toFloat()

val Long.d: Double get() = this.toDouble()

val Long.i: Int get() = this.toInt()

/** The Char array representing by this string */
val String.chars: Array<Char> get() = this.toCharArray().toTypedArray()
