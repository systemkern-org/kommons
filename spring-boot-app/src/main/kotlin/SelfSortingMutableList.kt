package com.systemkern.kommons

class SelfSortingMutableList<T : Comparable<T>>(
        private val delegate: MutableList<T> = mutableListOf()
) : MutableList<T> by delegate {
    override fun add(element: T): Boolean {
        var i = binarySearch(element)
        if (i < 0) i = i.inv()
        delegate.add(i, element)
        return true
    }
}