package com.systemkern.kommons

import java.math.BigDecimal

inline val Boolean.int: Int get() = if (this) 1 else 0

inline fun <T> Boolean.ifTrue(block: () -> T?): T? =
    if (this) block() else null

inline fun <T> Boolean.ifFalse(block: () -> T?): T? =
    if (!this) block() else null

inline val Int.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

inline val Int.d: Double get() = this.toDouble()

inline val Int.f: Float get() = this.toFloat()

inline val Int.l: Long get() = this.toLong()

inline val Double.f: Float get() = this.toFloat()

inline val Double.bd: BigDecimal get() = this.toBigDecimal()

inline val Double.i: Int get() = this.toInt()

inline val Double.l: Long get() = this.toLong()

inline val Float.bd: BigDecimal get() = this.toBigDecimal()

inline val Float.d: Double get() = this.toDouble()

inline val Float.i: Int get() = this.toInt()

inline val Float.l: Long get() = this.toLong()

inline val Long.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

inline val Long.f: Float get() = this.toFloat()

inline val Long.d: Double get() = this.toDouble()

inline val Long.i: Int get() = this.toInt()

/** The Char array representing by this string */
inline val String.ch: Array<Char> get() = this.toCharArray().toTypedArray()
