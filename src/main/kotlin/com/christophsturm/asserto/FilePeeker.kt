package com.christophsturm.asserto

import java.io.File
import java.io.FileReader

data class FileInfo(val lineNumber: Int, val sourceFileName: String, val line: String)

object FilePeeker {
    fun  getFileInfo(offset: Int = 2): FileInfo {
        val e = RuntimeException()
        val entry = e.stackTrace[offset]
        val className = entry.className
        val indexOfDollar = className.indexOf('$')
        val realClassName = if (indexOfDollar != -1) {
            className.substring(0, indexOfDollar)
        } else className
        val clazz = javaClass.classLoader.loadClass(realClassName)!!
        val classFile = File(clazz.protectionDomain.codeSource.location.path)
        val potentialFilename = classFile.absolutePath.replace("build/classes/test", "src/test/kotlin").plus("/" + realClassName.replace(".", "/")).plus(".kt")
        val line = FileReader(potentialFilename).useLines {
            it.drop(entry.lineNumber-1).first()
        }
        return FileInfo(entry.lineNumber, sourceFileName = potentialFilename, line = line)
    }
}
