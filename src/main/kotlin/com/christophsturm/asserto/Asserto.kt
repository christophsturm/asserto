package com.christophsturm.asserto

import junit.framework.AssertionFailedError

fun assertThat(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val condition = assertLine.substring(assertLine.indexOf('(')+1, assertLine.lastIndexOf(')'))
    throw AssertionFailedError("$condition was not true")
}

fun <T> capture(captured: T): T {
    return captured
}

fun assertThat(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


