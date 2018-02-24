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
        val baseName = classFile.absolutePath.replace("build/classes/test", "src/test/kotlin").plus("/" + realClassName.replace(".", "/"))
        val sourceFileName = if (File(baseName.plus(".kt")).exists()) baseName.plus(".kt") else baseName.plus(".java").replace("src/test/kotlin", "src/test/java")
        val line = FileReader(sourceFileName).useLines {
            it.drop(entry.lineNumber-1).first()
        }
        return FileInfo(entry.lineNumber, sourceFileName = sourceFileName, line = line)
    }
}
