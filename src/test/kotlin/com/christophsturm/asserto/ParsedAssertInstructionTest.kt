package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ParsedAssertInstructionTest {
    val niceInstruction = ParsedAssertInstruction("""expect(that(userId).equals("12"))""")
    val instructionWithRandomWhitespace = ParsedAssertInstruction("""expect(  that(  userId  ).   equals   ("12"))""")
    @Test fun `knows the variable name`() {
        assertEquals("userId", niceInstruction.variableName)
        assertEquals("userId", instructionWithRandomWhitespace.variableName)
    }
    @Test fun `recognizes method calls`() {
        assertEquals("equals", niceInstruction.methodName)
        assertEquals("equals", instructionWithRandomWhitespace.methodName)
    }
    @Test fun `recognizes method parameter`() {
        assertEquals("\"12\"", niceInstruction.methodParameter)
        assertEquals("\"12\"", instructionWithRandomWhitespace.methodParameter)
    }
}

