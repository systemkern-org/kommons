package com.systemkern.kommons

import org.w3c.dom.Document
import java.io.File
import java.io.IOException
import java.io.RandomAccessFile
import javax.xml.parsers.DocumentBuilderFactory


fun File.overwrite(content: String): File =
        this.overwrite(content.toByteArray())

fun File.overwrite(content: ByteArray): File {
    RandomAccessFile(this, "rw").apply {
        setLength(0) // set file length to zero effectively deleting all content
        write(content)
    }
    return this
}

fun File.ensureExists(): File {
    this.parentFile.mkdirs()
    if (!this.parentFile.exists())
        throw IOException("could not create parent folder of file with $path")
    if (!exists()) {
        createNewFile()
        if (!exists())
            throw IOException("Could not create file with $path.")
    }
    return this
}

fun File.readAsDocument(): Document =
        DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(this)
                .apply {
                    documentElement.normalize()
                }
