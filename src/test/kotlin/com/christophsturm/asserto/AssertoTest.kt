package com.christophsturm.asserto

import junit.framework.AssertionFailedError
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import org.junit.Test

class AssertoTest {
    @Test fun `works with methods that return boolean`() {
        val userId = "123"
        expect(that(userId).contains("23"))
        expect(that("23") in userId)
        val sameUserId = userId
        expect(that(userId) === sameUserId)
        expect(that(userId) == sameUserId)
    }


    @Test fun `can assert that an exception is thrown`() {
        expect({throw RuntimeException("blah")}, { e->e.message!!.contains("blah")})
    }
    @Test
    fun `creates useful error message for equals`() {
        val userId = "123"
        try {
            @Suppress("ReplaceCallWithComparison")
            expect(that(userId).equals("12"))
            fail()
        } catch (e:AssertionFailedError) {
            assertEquals("""expected that "userId" equals "12" but it was "123"""", e.message)
        }
    }

    @Test
    fun `creates an almost useful error message as fallback`() {
        val userId = "123"
        try {
            expect(userId == "12")
            fail()
        } catch (e:AssertionFailedError) {
            assertEquals("""userId == "12" was not true""", e.message)
        }
    }
}

