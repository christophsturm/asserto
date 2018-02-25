package com.christophsturm.asserto

import java.io.File
import java.io.FileReader

data class FileInfo(val lineNumber: Int, val sourceFileName: String, val line: String)

object FilePeeker {
    fun getCallerFileInfo(offset: Int = 2): FileInfo {
        val callerStackTraceElement = RuntimeException().stackTrace[offset]
        val className = callerStackTraceElement.className.substringBefore('$')
        val clazz = javaClass.classLoader.loadClass(className)!!
        val classFileAbsolutePath = File(clazz.protectionDomain.codeSource.location.path).absolutePath

        val buildDir =
                if (classFileAbsolutePath.contains("/out/"))
                    "out/test/classes" // running inside IDEA
                else if (classFileAbsolutePath.contains("build/classes/java"))
                    "build/classes/java/test" // gradle 4.x
                else
                    "build/classes/test" // older gradle

        val sourceFileWithoutExtension = classFileAbsolutePath.replace(buildDir, "src/test/kotlin").plus("/" + className.replace(".", "/"))
        val sourceFileName = if (File(sourceFileWithoutExtension.plus(".kt")).exists()) sourceFileWithoutExtension.plus(".kt") else sourceFileWithoutExtension.plus(".java").replace("src/test/kotlin", "src/test/java")

        val callerLine = FileReader(sourceFileName).useLines {
            it.drop(callerStackTraceElement.lineNumber - 1).first()
        }

        return FileInfo(callerStackTraceElement.lineNumber, sourceFileName = sourceFileName, line = callerLine)
    }
}
