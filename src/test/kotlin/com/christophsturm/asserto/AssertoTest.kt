package com.christophsturm.asserto

import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import org.junit.Ignore
import org.junit.Test

class AssertoTest {
    @Test fun `works with methods that return boolean`() {
        val userId = "123"
        assertThat(userId.contains("23"))
        assertThat("23" in userId)
        val sameUserId = userId
        assertThat(userId === sameUserId)
        assertThat(userId == sameUserId)
    }
    @Test fun `can assert that an exception is thrown`() {
        assertThat({throw RuntimeException("blah")}, {e->e.message!!.contains("blah")})
    }
    @Test
    @Ignore("this is going to be a bit of work")
    fun `creates useful error message for equals`() {
        val userId = "123"
        try {
            assertThat(userId == "12")
            fail()
        } catch (e:AssertionFailedError) {
            assertEquals("""expected "userId" to be equal to "12" but was "123" """, e.message)
        }
    }
    @Test
    fun `creates an almost useful error message`() {
        val userId = "123"
        try {
            assertThat(userId == "12")
            fail()
        } catch (e:AssertionFailedError) {
            assertEquals("""userId == "12" was not true""", e.message)
        }
    }
}

