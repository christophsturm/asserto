package com.christophsturm.asserto

import junit.framework.AssertionFailedError

fun assertThat(condition: Boolean) {
    if (condition === true)
        return
    throw AssertionFailedError("condition not met")
}

fun assertThat(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


