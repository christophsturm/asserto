package com.christophsturm.asserto

import junit.framework.AssertionFailedError

object Asserto {
    val threadLocal = ThreadLocal<Any>()
}
fun expect(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val condition = assertLine.substring(assertLine.indexOf('(') + 1, assertLine.lastIndexOf(')'))

    val parsed: ParsedAssertInstruction
    val message = try {
        parsed = ParsedAssertInstruction(condition)
        "expected that \"${parsed.variableName}\" ${parsed.methodName} ${parsed.methodParameter} but it was \"${Asserto.threadLocal.get()}\""
    } catch(e: RuntimeException) {
        "$condition was not true"
    }
    throw AssertionFailedError(message)

}

fun <T> that(captured: T): T {
    Asserto.threadLocal.set(captured)
    return captured
}

fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


