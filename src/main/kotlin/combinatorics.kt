package com.systemkern.kommons

fun calculateTreeSize(branches: Number, treeDepth: Number): Double = (((branches pow (treeDepth.l + 1)) - 1) / (branches.l - 1))

private infix fun Double.pow(other: Double): Double = Math.pow(this, other)
private infix fun Number.pow(other: Number): Double = Math.pow(this.toDouble(), other.toDouble())
