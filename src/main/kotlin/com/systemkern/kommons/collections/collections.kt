package com.systemkern.kommons

/**
 * Returns a new read-only list of all elements of the given collections
 * The returned list is serializable (JVM).
 * @since 0.2
 */
fun <T> flatten(vararg elements: List<T>): List<T> =
    elements.flatMap { it }

fun <T> flatten(list: List<List<T>>): List<T> =
    list.flatMap { it }

inline fun <reified T> union(l: List<T>, vararg elements: T): List<T> =
    flatten(l, elements.toList())

inline fun <reified T> union(l1: List<T>, l2: List<T>, vararg elements: T): List<T> =
    flatten(l1, l2, elements.toList())

inline fun <reified T> union(l1: List<T>, l2: List<T>, l3: List<T>, vararg elements: T): List<T> =
    flatten(l1, l2, l3, elements.toList())

inline fun <reified T> union(l1: List<T>, l2: List<T>, l3: List<T>, l4: List<T>, vararg elements: T): List<T> =
    flatten(l1, l2, l3, l4, elements.toList())

inline fun <reified T> union(l1: List<T>, l2: List<T>, l3: List<T>, l4: List<T>, l5: List<T>, vararg elements: T): List<T> =
    flatten(l1, l2, l3, l4, l5, elements.toList())

inline fun <T, R> Iterable<T>.mapToSet(transform: (T) -> R) =
    this.map(transform).toSet()

inline fun <T, R : Any> Iterable<T>.mapNotNullToSet(transform: (T) -> R?) =
    this.mapNotNull(transform).toSet()
