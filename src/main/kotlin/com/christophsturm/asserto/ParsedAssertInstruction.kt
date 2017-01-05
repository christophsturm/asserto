package com.christophsturm.asserto

class ParsedAssertInstruction(condition: String) {
    val subject: String
    val methodName: String
    val methodParameter: String
    init {
        val variableNameStart = condition.indexOf("that(") + 5
        val variableNameEnd = findMatchingClosingBracket(condition, variableNameStart)
        subject = condition.substring(variableNameStart, variableNameEnd).trim()
        if (condition[variableNameEnd+1] == '.') {
            val methodNameStart = variableNameEnd+2
            val methodNameEnd = condition.indexOf('(', methodNameStart)
            methodName = condition.substring(methodNameStart, methodNameEnd).trim()
            val parameterStart = methodNameEnd + 1
            val parameterEnd = findMatchingClosingBracket(condition, parameterStart)
            methodParameter = condition.substring(parameterStart, parameterEnd)
        } else {
            throw RuntimeException("could not parse $condition")
        }

    }

    private fun findMatchingClosingBracket(condition: String, start: Int): Int {
        val len = condition.length
        var bracketLevel = 0
        var pos = start
        while (pos < len) {
            when (condition[pos]) {
                '(' -> bracketLevel += 1
                ')' -> if (bracketLevel == 0) return pos else bracketLevel -= 1
            }
            pos += 1
        }
        throw RuntimeException("could not find matching brackets in $condition")
    }

}
