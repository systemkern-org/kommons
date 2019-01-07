package com.systemkern.kommons.xml

import org.w3c.dom.Node
import org.w3c.dom.NodeList

fun NodeList.forEach(action: (node: Node) -> Unit) =
    (0 until length).forEach { action(item(it)) }

fun <T> NodeList.map(transform: (Node) -> T): List<T> {
    val destination = ArrayList<T>()
    (0 until length).forEach {
        destination.add(
            transform(item(it))
        )
    }
    return destination
}

fun NodeList.filter(predicate: (Node) -> Boolean): List<Node> {
    val destination = ArrayList<Node>()
    (0 until length).forEach {
        val element = item(it)
        if (predicate(element))
            destination.add(element)
    }
    return destination
}