package com.systemkern.kommons

import org.w3c.dom.Document
import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node
import org.w3c.dom.NodeList
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

// document
fun Document.forEach(tag: String = "*", action : (node: Node) -> Unit) =
    getElementsByTagName(tag).forEach(action)


// node
inline fun NamedNodeMap.forEach(action: (node: Node) -> Unit) =
    (0 until this.length).forEach { action(item(it)) }

// nodelist
inline fun NodeList.forEach(action: (node: Node) -> Unit) =
    (0 until length).forEach { action(item(it)) }

inline fun <T> NodeList.map(transform: (Node) -> T): List<T> {
    val destination = ArrayList<T>()
    (0 until length).forEach {
        destination.add(
            transform(item(it))
        )
    }
    return destination
}

inline fun NodeList.filter(predicate: (Node) -> Boolean): List<Node> {
    val destination = ArrayList<Node>()
    (0 until length).forEach {
        val element = item(it)
        if (predicate(element))
            destination.add(element)
    }
    return destination
}

// generic
/**
 * Receives an xml document as input stream, parses it and return the corresponding Document. If the stream is not a
 * valid XML document then throws an exception.
 */
fun readDocument(stream: InputStream): Document =
    DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .parse(stream)
        .apply { documentElement.normalize() }
        ?: throw RuntimeException("Could not Parse XML Document from Stream")