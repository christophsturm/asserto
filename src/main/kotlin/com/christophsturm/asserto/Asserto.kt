package com.christophsturm.asserto

import junit.framework.AssertionFailedError

fun expect(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val condition = assertLine.substring(assertLine.indexOf('(')+1, assertLine.lastIndexOf(')'))
    throw AssertionFailedError("$condition was not true")
}

fun <T> that(captured: T): T {
    return captured
}

fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


