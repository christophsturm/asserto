package com.christophsturm.asserto;

import org.junit.Test;


public class AssertoJavaTest {
    @Test
    public void worksWithMethodsThatReturnBoolean() {
        String userId = "123";
        Asserto.that(userId).contains("23");
        JavaClass.that(userId).contains("23");
    }

}
