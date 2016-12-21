package com.christophsturm.asserto

import junit.framework.AssertionFailedError

fun assertThat(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    throw AssertionFailedError("$assertLine failed")
}

fun assertThat(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


