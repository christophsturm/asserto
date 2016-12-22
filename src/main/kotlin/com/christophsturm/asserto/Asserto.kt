package com.christophsturm.asserto

import junit.framework.AssertionFailedError

object Asserto {
    val threadLocal = ThreadLocal<Any>()
}
fun expect(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val conditionString = assertLine.substring(assertLine.indexOf('(')+1, assertLine.lastIndexOf(')'))
    throw AssertionFailedError("$conditionString was not true")
}

fun <T> that(captured: T): T {
    Asserto.threadLocal.set(captured)
    return captured
}

fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


