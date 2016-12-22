package com.christophsturm.asserto

class ParsedAssertInstruction(condition: String) {
    val variableName: String
    val methodName: String
    val methodParameter: String
    init {
        val variableNameStart = condition.indexOf("that(") + 5
        val variableNameEnd = condition.indexOf(')', variableNameStart)
        variableName = condition.substring(variableNameStart, variableNameEnd).trim()
        if (condition[variableNameEnd+1] == '.') {
            val methodNameStart = variableNameEnd+2
            val methodNameEnd = condition.indexOf('(', methodNameStart)
            methodName = condition.substring(methodNameStart, methodNameEnd).trim()
            val parameterStart = methodNameEnd + 1
            val parameterEnd = condition.indexOf(')', parameterStart)
            methodParameter = condition.substring(parameterStart, parameterEnd)
        } else {
            throw RuntimeException("could not parse $condition")
        }

    }

}
