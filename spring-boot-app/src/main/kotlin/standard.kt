package com.systemkern.kommons

inline fun <T> T.alsoIfTrue(boolean: Boolean, block: (T) -> Unit): T {
    if (boolean) block(this)
    return this
}

inline fun <T> T.alsoIfFalse(boolean: Boolean, block: (T) -> Unit): T {
    if (!boolean) block(this)
    return this
}

inline fun <T, R> T.letIfTrue(boolean: Boolean, block: (T) -> R): R? =
    if (boolean) block(this) else null

inline fun <T, R> T.letIfFalse(boolean: Boolean, block: (T) -> R): R? =
    if (!boolean) block(this) else null
