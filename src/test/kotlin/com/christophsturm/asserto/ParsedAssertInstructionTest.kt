package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ParsedAssertInstructionTest {
    val niceInstruction = ParsedAssertInstruction("""expect(that(userId).equals("12"))""")
    val instructionWithRandomWhitespace = ParsedAssertInstruction("""expect(  that(  userId  ).   equals   ("12"))""")
    @Test fun `knows the subject`() {
        assertEquals("userId", niceInstruction.subject)
        assertEquals("userId", instructionWithRandomWhitespace.subject)
    }

    @Test fun `knows the called method`() {
        assertEquals("equals", niceInstruction.methodName)
        assertEquals("equals", instructionWithRandomWhitespace.methodName)
    }

    @Test fun `knows the method parameter`() {
        assertEquals("\"12\"", niceInstruction.methodParameter)
        assertEquals("\"12\"", instructionWithRandomWhitespace.methodParameter)
    }
}

