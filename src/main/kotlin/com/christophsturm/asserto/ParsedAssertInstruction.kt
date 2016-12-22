package com.christophsturm.asserto

class ParsedAssertInstruction(s: String) {
    val condition= s.substring(s.indexOf('(')+1, s.lastIndexOf(')'))
    val variableNameStart = condition.indexOf("that(")+5
    val variableNameEnd = condition.indexOf(')', variableNameStart)
    val variableName = condition.substring(variableNameStart, variableNameEnd).trim()
    val methodName: String?
    val methodParameter: String?
    init {
        if (condition[variableNameEnd+1] == '.') {
            val methodNameStart = variableNameEnd+2
            val methodNameEnd = condition.indexOf('(', methodNameStart)
            methodName = condition.substring(methodNameStart, methodNameEnd).trim()
            val parameterStart = methodNameEnd + 1
            val parameterEnd = condition.indexOf(')', parameterStart)
            methodParameter = condition.substring(parameterStart, parameterEnd)
        } else {
            methodName = null
            methodParameter = null
        }

    }

}
