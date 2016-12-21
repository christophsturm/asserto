package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

class FilePeekerTest {
    @Test fun `can get FileInfo`() {
        val fileInfo = FilePeeker.getFileInfo()

        assertEquals(9, fileInfo.lineNumber)
        assertTrue(fileInfo.sourceFileName, fileInfo.sourceFileName.endsWith("src/test/kotlin/com/christophsturm/asserto/FilePeekerTest.kt"))
        assertEquals("val fileInfo = FilePeeker.getFileInfo()", fileInfo.line.trim())
    }
}

