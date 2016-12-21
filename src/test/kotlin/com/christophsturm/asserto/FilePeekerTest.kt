package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test
import java.io.File

class FilePeekerTest {
    @Test fun `can get FileInfo`() {
        val fileInfo = FilePeeker.getFileInfo()
        assertEquals(10, fileInfo.lineNumber)
        assertTrue(fileInfo.sourceFileName, fileInfo.sourceFileName.endsWith("src/test/kotlin/com/christophsturm/asserto/FilePeekerTest.kt"))
    }

}

data class FileInfo(val lineNumber:Int, val sourceFileName:String)

object FilePeeker {
    fun  getFileInfo(): FileInfo {
        val e = RuntimeException()
        val entry = e.stackTrace[1]
        val className = entry.className
        val clazz = javaClass.classLoader.loadClass(className)!!
        val classFile = File(clazz.getProtectionDomain().getCodeSource().getLocation().getPath())
        val potentialFilename = classFile.absolutePath.replace("build/classes/test", "src/test/kotlin").plus("/"+className.replace(".","/")).plus(".kt")
        if (!File(potentialFilename).exists())
            throw RuntimeException("no idea where that source file is")
        return FileInfo(entry.lineNumber, sourceFileName = potentialFilename)
    }
}

