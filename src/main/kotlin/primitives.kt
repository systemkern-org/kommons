package com.systemkern.kommons

import java.math.BigDecimal

inline val Boolean.int: Int get() = if (this) 1 else 0

inline fun <T> Boolean.ifTrue(block: () -> T?): T? =
    if (this) block() else null

inline fun <T> Boolean.ifFalse(block: () -> T?): T? =
    if (!this) block() else null


inline val Number.d: Double get() = this.toDouble()

inline val Number.f: Float get() = this.toFloat()

inline val Number.l: Long get() = this.toLong()

inline val Number.i: Int get() = this.toInt()

inline val Double.bd: BigDecimal get() = this.toBigDecimal()

inline val Float.bd: BigDecimal get() = this.toBigDecimal()

inline val Int.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

inline val Long.bd: BigDecimal get() = this.toBigDecimal().setScale(0)

/** The Char array representing by this string */
inline val String.ch: Array<Char> get() = this.toCharArray().toTypedArray()
