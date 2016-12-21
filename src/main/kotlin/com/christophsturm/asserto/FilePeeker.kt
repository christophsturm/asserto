package com.christophsturm.asserto

import java.io.File

data class FileInfo(val lineNumber:Int, val sourceFileName:String)

object FilePeeker {
    fun  getFileInfo(offset: Int = 1): FileInfo {
        val e = RuntimeException()
        val entry = e.stackTrace[offset]
        val className = entry.className
        val clazz = javaClass.classLoader.loadClass(className)!!
        val classFile = File(clazz.protectionDomain.codeSource.location.path)
        val potentialFilename = classFile.absolutePath.replace("build/classes/test", "src/test/kotlin").plus("/" + className.replace(".", "/")).plus(".kt")
        return FileInfo(entry.lineNumber, sourceFileName = potentialFilename)
    }
}
