package com.systemkern.kommons

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.io.IOException
import java.nio.charset.Charset.defaultCharset

class FileExtensionsTests {
    private val pathToProject: String  = System.getProperty("user.dir")
    private val fileName = "$pathToProject/src/test/resources/osx.txt"
    private lateinit var file: File

    @BeforeEach
    fun setup(){
        file = File(fileName)
        file.writeText("This file\ncontains line endings\nwoho\nanother line\nEOF", defaultCharset())
    }

    @Test fun `Can read OSX Line Endings 2`() {
        assertThat(file.readLines())
            .isEqualTo(
                arrayListOf("This file", "contains OSX line endings", "woho", "another line", "EOF")
            )
    }

    @Test fun `Can override file`(){
        val newContent = "content\nthis is the new content\nfor testing purposes"
        assertThat(file.overwrite(newContent).readLines()).isEqualTo(
            arrayListOf("content","this is the new content","for testing purposes")
        )
    }

    @Test fun `Can ensure file exists`(){
        val fakeFileName = "some/inexistent/path/to/file.txt"
        assertThat(catchThrowable { File(fakeFileName).ensureExists() }).isInstanceOf(IOException::class.java)
    }
}
