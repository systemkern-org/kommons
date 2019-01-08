package com.systemkern.kommons

val Int.d: Double get() = this.toDouble()

val Int.f: Float get() = this.toFloat()

val Int.l: Long get() = this.toLong()

val String.chars: Array<Char> get() = this.toCharArray().toTypedArray()
