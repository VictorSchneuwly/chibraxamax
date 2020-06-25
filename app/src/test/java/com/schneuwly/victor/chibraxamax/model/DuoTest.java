package com.schneuwly.victor.chibraxamax.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * <class description>
 *
 * @author Victor Schneuwly
 */
public class DuoTest{
    private final Player martin = new Player("Martin", 0,0,0);
    private final Player guillaume = new Player("Guillaume", 0,0,0);
    private final Duo mg = new Duo(martin,guillaume);

    @Test
    public void referenceTest() {
        mg.win();
        mg.lose();
        mg.addPoints(70);

        assertEquals(70, mg.getTotalPoints());
        assertEquals(1, martin.getWins());
        assertEquals(1, martin.getLosses());
        assertEquals(2, martin.getTotalGamesPlayed());
        assertEquals(1, guillaume.getWins());
        assertEquals(1, guillaume.getLosses());
        assertEquals(2, guillaume.getTotalGamesPlayed());

        mg.addAnnounce(martin,30);
        mg.addAnnounce(guillaume,40);
        assertEquals(30, martin.getTotalAnnounce());
        assertEquals(40, guillaume.getTotalAnnounce());
        assertEquals(15, martin.getAnnouncePerGame(),0);
        assertEquals(20, guillaume.getAnnouncePerGame(),0);
        assertEquals(140, mg.getTotalPoints());
    }

    @Test
    public void containsTest() {
        assertTrue(mg.contains(martin));
        assertTrue(mg.contains(guillaume));
    }

    @Test
    public void equalsTest() {
        Duo duoTest = new Duo(martin,guillaume);
        assertEquals(mg, duoTest);
    }

    @Test
    public void hashCodeTest() {
        Duo duoTest = new Duo(martin,guillaume);
        assertEquals(mg.hashCode(), duoTest.hashCode());
    }
}