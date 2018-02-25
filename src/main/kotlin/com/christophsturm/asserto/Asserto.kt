@file:JvmName("Asserto")
package com.christophsturm.asserto


val subjectValueThreadLocal = ThreadLocal<Any>()
fun expect(condition: Boolean) {
    if (condition)
        return
    val assertLine = FilePeeker.getCallerFileInfo(2).line.trim()
    val conditionString = assertLine.substring(assertLine.indexOf('(') + 1, assertLine.lastIndexOf(')'))

    val message = try {
        val parsed = ParsedAssertInstruction(conditionString)
        val parameterWithSpace = if (parsed.methodParameter.isEmpty()) "" else " ${parsed.methodParameter}"
        "expected that \"${parsed.subject}\" ${parsed.methodName}$parameterWithSpace but it was \"${subjectValueThreadLocal.get()}\""
    } catch(e: RuntimeException) {
        "$conditionString was not true"
    }
    throw AssertionError(message)

}

fun <T> that(captured: T): T {
    subjectValueThreadLocal.set(captured)
    return captured
}

@Suppress("UNUSED_PARAMETER")
fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


