package com.schneuwly.victor.chibraxamax.controller;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GameActivityTest
 *
 * @author Victor Schneuwly
 */
public class GameActivityTest {
    @Test
    public void simpleTallyMarksTest() {
        assertEquals("a", GameActivity.tallyMarks(1));
        assertEquals("b", GameActivity.tallyMarks(2));
        assertEquals("c", GameActivity.tallyMarks(3));
        assertEquals("d", GameActivity.tallyMarks(4));
        assertEquals("e", GameActivity.tallyMarks(5));
    }

    @Test
    public void longerTallyMarksTest() {
        assertEquals("eeeeeeeeeed", GameActivity.tallyMarks(54));
        assertEquals("eeeeb", GameActivity.tallyMarks(22));
        assertEquals("eeea", GameActivity.tallyMarks(16));
        assertEquals("eeeeeeeeeeeeeeeeeeeec", GameActivity.tallyMarks(103));
        assertEquals("eeeeeeeeeeeee", GameActivity.tallyMarks(65));

    }
}