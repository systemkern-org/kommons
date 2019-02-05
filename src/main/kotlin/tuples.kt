package com.systemkern.kommons

infix fun <A, B, C> Pair<A, B>.to(third: C): Triple<A, B, C> = Triple(first, second, third)

infix fun <A, B, C> A.to(pair: Pair<B, C>): Triple<A, B, C> = Triple(this, pair.first, pair.second)
