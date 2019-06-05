package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.io.File
import java.io.InputStream

class FileExtensionsTests {
    private val pathToProject: String  = System.getProperty("user.dir")

    @Test fun `Can read OSX Line Endings`() {
        val fileName = "$pathToProject/src/test/resources/osx.txt"
        print(fileName)
        val inputStream: InputStream = File(fileName).inputStream()
        val lineList = mutableListOf<String>()

        File(fileName).useLines { lines -> lines.forEach { lineList.add(it) } }

        inputStream.bufferedReader().useLines { lines ->
            lines.forEach {
                print(it)
            }
        }
    }

    @Test fun `Can read OSX Line Endings 2`() {
        val fileName = ""
        val lineList = File(fileName).readLines()
        assertThat(lineList).isEqualTo(
                arrayListOf<String>("This file", "contains OSX line endings", "woho", "another line", "EOF")
        )
    }
}


/*

public fun File.forEachLine(charset: Charset = Charsets.UTF_8, action: (line: String) -> Unit): Unit {
    // Note: close is called at forEachLine
    BufferedReader(InputStreamReader(FileInputStream(this), charset)).forEachLine(action)
}
inline fun <T> File.useLines(charset: Charset = Charsets.UTF_8, block: (Sequence<String>) -> T): T =
    bufferedReader(charset).use {
        block(it.lineSequence())
    }
 */

