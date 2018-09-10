package com.christophsturm.asserto;


import org.junit.jupiter.api.Test;

import static com.christophsturm.asserto.Asserto.expect;
import static com.christophsturm.asserto.Asserto.that;
import static org.junit.jupiter.api.Assertions.assertEquals;


class AssertoJavaTest {
    @Test
    void worksFromJava() {
        String userId = "123";
        expect(that(userId).contains("23"));
        try {
            expect(that(userId).contains("13"));
        } catch (AssertionError e) {
            assertEquals("expected that \"userId\" contains \"13\" but it was \"123\"", e.getMessage());
        }
    }

}
