package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.w3c.dom.Document
import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File

internal class NodeListTest {

    private val XML = "src/test/resources/file/xml/test.xml"
    private lateinit var document: Document

    @BeforeEach fun before() {
        document = readDocument(File(XML).inputStream())
    }

    @Test fun `Can get each node from nodelist when nodelist is not empty`() {
        val names = ArrayList<Node>()
        document.getElementsByTagName("Employee").forEach { names.add(it) }
        assertThat(names).isNotEmpty
    }

    @Test fun `Can get each node from nodelist when nodelist empty`() {
        val names = mutableListOf<Node>()
        document.getElementsByTagName("Boss").forEach { names.add(it) }
        assertThat(names).isEmpty()
    }

    @Test fun `Can filter nodes by id when nodelist not empty`() {
        val filtered = document.getElementsByTagName("Employee").filter {
            it is Element && it.getAttribute("id") == "2"
        }
        assertThat(filtered).isNotEmpty
    }

    @Test fun `Can map nodes when nodelist not empty`() {
        val filtered = document.getElementsByTagName("Employee").map {
            it as Element
            it.getAttribute("id").toInt()
        }
        assertThat(filtered).containsAll(listOf(1, 2))
    }
}