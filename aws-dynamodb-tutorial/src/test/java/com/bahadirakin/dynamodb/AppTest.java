package com.bahadirakin.dynamodb;

import org.junit.Assert;
import org.junit.Test;

public class AppTest {

    @Test
    public void testMain() throws Exception {

        final String[] noArg = new String[]{};

        try {
            App.main(noArg);
        } catch (Exception e) {
            Assert.fail("Main method fails!");
        }
    }
}