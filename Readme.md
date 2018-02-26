# asserto - the simple assertion library for kotlin and java

Assertion libraries should be as simple as possible, while producing helpful output in case of a failed assertion.

Introducing asserto, the simplest possible assertion library.

There are no custom matchers. You can use any method that returns boolean and takes one or zero parameters in your assertion.

just look at `AssertoTest` and `AssertoJavaTest` for examples.

asserto reads the sourcecode and parses the assert line to give you a meaningful error-message.

        val userId = "123"
        expect(that(userId).equals("12"))
will produce this error message:

        expected that "userId" equals "12" but it was "123"
        
you can also call methods on the test subject and on the value you compare with
        
        expect(that(userId.toLowerCase()).equals("12".toUpperCase()))

will output 

    expected that "userId.toLowerCase" equals "12" but it was "123"

it also works if you use an operator instead of a method call

        expect(that(userId.toLowerCase()) == "12".toUpperCase())

will output 

    expected that "userId.toLowerCase" == "12" but it was "123"
    
### building    
Import into IDEA as gradle project or run the test suite in gradle via `./gradlew check`

missing:
* capturing non constants to assert with. 
`expect(that(userId).equals(otherUserId))` will output `expected that "userId" equals otherUserId but it was "123"`
* asserting on thrown exceptions.
* special handling of equals to give a diff output that integrates well with IDEA
* more robust source code lookup. (but WFM)
* a release. if anybody wants to use it

please note that for now this is just an experiment that i wrote over christmas 2016 and that I'm currently not using
on any production project. Still I think it could be interesting to others so here it is. 
  
