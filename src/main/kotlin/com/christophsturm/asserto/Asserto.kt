package com.christophsturm.asserto


object Asserto {
    val threadLocal = ThreadLocal<Any>()
}
fun expect(condition: Boolean) {
    if (condition === true)
        return
    val assertLine = FilePeeker.getFileInfo(2).line.trim()
    val conditionString = assertLine.substring(assertLine.indexOf('(') + 1, assertLine.lastIndexOf(')'))

    val message = try {
        val parsed: ParsedAssertInstruction = ParsedAssertInstruction(conditionString)
        val parameterWithSpace = if (parsed.methodParameter.isEmpty()) "" else " ${parsed.methodParameter}"
        "expected that \"${parsed.subject}\" ${parsed.methodName}$parameterWithSpace but it was \"${Asserto.threadLocal.get()}\""
    } catch(e: RuntimeException) {
        "$conditionString was not true"
    }
    throw AssertionError(message)

}

fun <T> that(captured: T): T {
    Asserto.threadLocal.set(captured)
    return captured
}

@Suppress("UNUSED_PARAMETER")
fun expect(block: () -> Nothing, condition: (e:Throwable) -> Boolean) {
}


