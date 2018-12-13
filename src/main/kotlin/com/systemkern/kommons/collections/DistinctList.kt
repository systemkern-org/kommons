package com.systemkern.kommons.com.systemkern.kommons.collections

/**
 * A Wrapper around LinkedHashSet<T> to provide a List<T> interface.
 * Since LinkedHashSet<T> is used as an implementation, DistinctList will
 * behave exactly like it.
 * @see LinkedHashSet
 */
class DistinctList<T> internal constructor(
    private val delegate: LinkedHashSet<T> = LinkedHashSet<T>()
) : List<T> {
    override fun contains(element: T): Boolean = delegate.contains(element)

    override fun containsAll(elements: Collection<T>): Boolean = delegate.containsAll(elements)

    override fun get(index: Int): T {
        TODO("not implemented")
    }

    override fun indexOf(element: T): Int {
        TODO("not implemented")
    }

    override fun isEmpty(): Boolean = delegate.isEmpty()

    override fun iterator(): Iterator<T> = delegate.iterator()

    override fun lastIndexOf(element: T): Int {
        TODO("not implemented")
    }

    override fun listIterator(): ListIterator<T> {
        TODO("not implemented")
    }

    override fun listIterator(index: Int): ListIterator<T> {
        TODO("not implemented")
    }

    override val size: Int = delegate.size

    override fun subList(fromIndex: Int, toIndex: Int): List<T> {
        TODO("not implemented")
    }
}

/**
 * Returns an immutable list containing only the specified objects [elements].
 * The returned list is serializable.
 */
fun <T> distinctListOf(vararg elements: T): List<T> =
    if (elements.isEmpty()) DistinctList<T>()
    else LinkedHashSet<T>(elements.size)
        .apply { this.addAll(elements) }
        .let { DistinctList(it) }

