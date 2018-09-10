package com.christophsturm.asserto

import junit.framework.TestCase.assertEquals
import org.junit.Test

class ParsedAssertInstructionTest {
    val niceInstruction = ParsedAssertInstruction("""that(userId.toUpperCase()).equals("12".toLowerCase())""")
    val instructionWithRandomWhitespace =
        ParsedAssertInstruction("""  that(  userId.toUpperCase()  ).   equals   ("12".toLowerCase())""")

    @Test
    fun `knows the subject`() {
        assertEquals("userId.toUpperCase()", niceInstruction.subject)
        assertEquals("userId.toUpperCase()", instructionWithRandomWhitespace.subject)
    }

    @Test
    fun `knows the called method`() {
        assertEquals("equals", niceInstruction.methodName)
        assertEquals("equals", instructionWithRandomWhitespace.methodName)
    }

    @Test
    fun `knows the method parameter`() {
        assertEquals("\"12\".toLowerCase()", niceInstruction.methodParameter)
        assertEquals("\"12\".toLowerCase()", instructionWithRandomWhitespace.methodParameter)
    }

    @Test
    fun `returns the rest of the string if it's not a method call`() {
        val assert = ParsedAssertInstruction("""that(userId.toUpperCase()) == "12".toLowerCase()""")
        assertEquals("userId.toUpperCase()", assert.subject)
        assertEquals("""== "12".toLowerCase()""", assert.methodName)
        assertEquals("", assert.methodParameter)
    }
}

