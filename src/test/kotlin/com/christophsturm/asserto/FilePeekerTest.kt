package com.christophsturm.asserto

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class FilePeekerTest {
    @Test
    fun `can get FileInfo`() {
        val fileInfo = FilePeeker.getCallerFileInfo()

        assertTrue(
            fileInfo.sourceFileName.endsWith("src/test/kotlin/com/christophsturm/asserto/FilePeekerTest.kt"),
            fileInfo.sourceFileName
        )
        assertEquals("val fileInfo = FilePeeker.getCallerFileInfo()", fileInfo.line.trim())
    }

    @Test
    fun `can get FileInfo for a block`() {
        val fileInfo = { FilePeeker.getCallerFileInfo() }()

        assertTrue(
            fileInfo.sourceFileName.endsWith("src/test/kotlin/com/christophsturm/asserto/FilePeekerTest.kt"),
            fileInfo.sourceFileName
        )
        assertEquals("val fileInfo = { FilePeeker.getCallerFileInfo() }()", fileInfo.line.trim())
    }
}

