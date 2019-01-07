package com.systemkern.kommons.xml

import org.w3c.dom.NamedNodeMap
import org.w3c.dom.Node

fun Node.forEach() : Unit = TODO()

fun Node.flatMap()  : Unit = TODO()

fun Node.filter() : Unit = TODO()

fun NamedNodeMap.forEach(action: (node: Node) -> Unit) =
    (0 until this.length).forEach { action(item(it)) }