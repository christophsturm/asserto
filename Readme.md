#asserto - the simple assertion library

Assertion libraries should be as simple as possible, while producing helpful output in case of a failed assertion.

introducing asserto, the simplest possible assertion library

        val userId = "123"
        expect(that(userId).equals("12"))
will produce this error message:

        expected that "userId" equals "12" but it was "123"
        
you can use any method that returns boolean and gets zero or one parameters ins your assertion.

missing:
* asserting on thrown exceptions.
* support for operators like `==` and `===`
* more robust source code lookup. (but WFM)
