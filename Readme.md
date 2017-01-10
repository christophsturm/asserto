#asserto - the simple assertion library

Assertion libraries should be as simple as possible, while producing helpful output in case of a failed assertion.

introducing asserto, the simplest possible assertion library
you can use any method that returns boolean and takes one or zero parameters ins your assertion.

asserto parses the assert line from the sourcecode to give you a meaningful errormessage.

        val userId = "123"
        expect(that(userId).equals("12"))
will produce this error message:

        expected that "userId" equals "12" but it was "123"
        
you can also call methods on the test subject and on the value you compare with
        
        expect(that(userId.toLowerCase()).equals("12".toUpperCase()))

if you use an opratator instead of a method call, it also works:

        expect(that(userId.toLowerCase()) == "12".toUpperCase())

will output 

    expected that "userId.toLowerCase" == "12" but it was "123"
    
missing:
* capturing non constants to assert with. 
`expect(that(userId).equals(otherUserId))` will output `expected that "userId" equals otherUserId but it was "123"`
* asserting on thrown exceptions.
* special handling of equals to give a diff output that integrates well with IDEA
* more robust source code lookup. (but WFM)
