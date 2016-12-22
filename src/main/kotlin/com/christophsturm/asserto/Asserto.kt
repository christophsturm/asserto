package com.christophsturm.asserto

import junit.framework.AssertionFailedError
import junit.framework.TestCase

object Asserto {
    val threadLocal = ThreadLocal<Any>()
}
fun expect(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val parsed  = ParsedAssertInstruction(assertLine)
    throw AssertionFailedError("expected that \"${parsed.variableName}\" ${parsed.methodName} ${parsed.methodParameter} but it was \"${Asserto.threadLocal.get()}\"")

}

fun <T> that(captured: T): T {
    Asserto.threadLocal.set(captured)
    return captured
}

fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


