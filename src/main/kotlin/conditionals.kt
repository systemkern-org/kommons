package com.systemkern.kommons


inline fun notIf(condition: Boolean, block: () -> Unit) {
    if (!condition)
        block()
}

inline fun ifNull(obj: Any?, block: () -> Unit) {
    if (obj == null)
        block()
}
