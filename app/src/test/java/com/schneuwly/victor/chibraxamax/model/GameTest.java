package com.schneuwly.victor.chibraxamax.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Game tests
 *
 * @author Victor Schneuwly
 */
public class GameTest {

    @Test
    public void test() {
        String[] test = {"fromage", "test", "gouda", "parmesan", "kwak", "fondue", "fraise"};

        for (int j = 0; j < 1000; j++) {
            int i = 0;
            for (String str : test) {
                assertEquals(str,test[i++]);
            }
        }

    }
}