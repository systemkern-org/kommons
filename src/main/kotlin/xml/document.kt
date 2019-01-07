package com.systemkern.kommons.xml

import org.w3c.dom.Document
import org.w3c.dom.Node

fun Document.forEach(tag: String = "*", action : (node: Node) -> Unit) {
    getElementsByTagName(tag).forEach(action)
}

fun Document.flatMap() : Unit = TODO()
