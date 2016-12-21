package com.christophsturm.asserto

import org.junit.Test

class AssertoTest {
    @Test fun `works with methods that return boolean`() {
        val userId = "123"
        assertThat(userId.contains("23"))
        assertThat("23" in userId)
        val sameUserId = userId
        assertThat(userId == sameUserId)
    }
    @Test fun `can assert that an exception is thrown`() {
        assertThat({throw RuntimeException()}, {e->e.message!!.contains("blah")})
    }
}
private fun assertThat(condition: Boolean) {
}

private fun assertThat(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}

