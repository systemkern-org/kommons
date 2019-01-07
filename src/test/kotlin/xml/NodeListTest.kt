package com.systemkern.kommons.xml

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import java.io.InputStream
import javax.xml.parsers.DocumentBuilderFactory

class NodeListTest {

    private val XML = "src/test/resources/file/xml/test.xml"
    private lateinit var document: Document

    @BeforeEach fun before() {
        document = readDocument(File(XML).inputStream())
    }

    @Test fun `when nodelist not empty then get each node from nodelist`() {
        val names = ArrayList<Node>()
        document.getElementsByTagName("Employee").forEach { names.add(it) }
        assertThat(names).isNotEmpty
    }

    @Test fun `when nodelist empty then get each node from nodelist`() {
        val names = ArrayList<Node>()
        document.getElementsByTagName("Boss").forEach { names.add(it) }
        assertThat(names).isEmpty()
    }

    @Test fun `when nodelist not empty then filter nodes by id`() {
        val filtered = document.getElementsByTagName("Employee").filter {
            it is Element && it.getAttribute("id") == "2"
        }
        assertThat(filtered).isNotEmpty
    }

    @Test fun `when nodelist not empty then map nodes`() {
        val filtered : List<Any> = document.getElementsByTagName("Employee").map {
            it as Element
            it.getAttribute("id").toInt()
        }
        assertThat(filtered).containsAnyOf(1, 2)
    }
}