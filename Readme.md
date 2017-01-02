#asserto - the simple assertion library

Assertion libraries should be as simple as possible, while producing helpful output in case of a failed assertion.

introducing asserto, the simplest possible assertion library
you can use any method that returns boolean and takes one or zero parameters ins your assertion.

asserto parses the assert line from the sourcecode to give you a meaningful errormessage.

        val userId = "123"
        expect(that(userId).equals("12"))
will produce this error message:

        expected that "userId" equals "12" but it was "123"
        

missing:
* capturing non constants to assert with. 
`expect(that(userId).equals(otherUserId))` will output `expected that "userId" equals otherUserId but it was "123"`
* asserting on thrown exceptions.
* support for operators like `==` and `===`
* special handling of equals to give a diff output that integrates well with IDEA
* more robust source code lookup. (but WFM)
