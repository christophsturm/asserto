package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.fail
import org.junit.Test

class AssertoTest {
    @Test
    fun `works with methods that return boolean`() {
        val userId = "123"
        expect(that(userId).contains("23"))
        expect(that("23") in userId)
        val sameUserId = userId
        expect(that(userId) === sameUserId)
        expect(that(userId) == sameUserId)
    }


    @Test
    fun `can assert that an exception is thrown`() {
        // this is not yet implemented and just an example how exception assertion should/could/will look like
        expect({ throw RuntimeException("blah") }, { e -> e.message!!.contains("blah") })
    }

    @Test
    fun `creates useful error message for equals`() {
        val userId = "123"
        try {
            @Suppress("ReplaceCallWithComparison") expect(that(userId.toUpperCase()).equals("12"))
            fail()
        } catch (e: AssertionError) {
            assertEquals("""expected that "userId.toUpperCase()" equals "12" but it was "123"""", e.message)
        }
    }

    @Test
    fun `works with methods returning boolean`() {
        fun checkMessage(block: () -> Unit, message: String) {
            try {
                block()
                fail()
            } catch (e: AssertionError) {
                assertEquals(message, e.message)
            }
        }

        val superName = "supergirl"
        checkMessage(
            { expect(that(superName).endsWith("man")) },
            """expected that "superName" endsWith "man" but it was "supergirl""""
        )
        checkMessage(
            { expect(that(superName).contains("atman")) },
            """expected that "superName" contains "atman" but it was "supergirl""""
        )
        checkMessage(
            { expect(that(superName).isEmpty()) }, """expected that "superName" isEmpty but it was "supergirl""""
        )
    }


    @Test
    fun `creates an error message that shows the actual value if captured`() {
        val userId = "123"
        try {
            expect(that(userId) == "12")
            fail()
        } catch (e: AssertionError) {
            assertEquals("""expected that "userId" == "12" but it was "123"""", e.message)
        }
    }

    @Test
    fun `creates an almost useful error message as fallback`() {
        val userId = "123"
        try {
            expect(userId == "12")
            fail()
        } catch (e: AssertionError) {
            assertEquals("""userId == "12" was not true""", e.message)
        }
    }


}

