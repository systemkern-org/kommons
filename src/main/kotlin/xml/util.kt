package com.systemkern.kommons.xml

import org.w3c.dom.Document
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

internal fun readDocument(stream: InputStream): Document =
    DocumentBuilderFactory.newInstance()
        .newDocumentBuilder()
        .parse(stream)
        .apply {
            documentElement.normalize()
        }
        ?: throw RuntimeException("Could not Parse XML Document from Stream")