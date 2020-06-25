package com.schneuwly.victor.chibraxamax.model;

//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * <class description>
 *
 * @author Victor Schneuwly
 */
public class DuoTest {
    private final Player martin = new Player("Martin", new Statistics(0, 0));
    private final Player guillaume = new Player("Guillaume", new Statistics(0, 0));
    private final Duo mg = new Duo(martin, guillaume, new Statistics(0, 0));

    @Test
    public void referenceTest() {
        mg.win();
        mg.lose();
        mg.addPoints(70);

        assertEquals(70, mg.getTotalPoints());
        assertEquals(1, martin.getStatistics().getWins());
        assertEquals(1, martin.getStatistics().getLosses());
        assertEquals(2, martin.getStatistics().getTotalGamesPlayed());
        assertEquals(1, guillaume.getStatistics().getWins());
        assertEquals(1, guillaume.getStatistics().getLosses());
        assertEquals(2, guillaume.getStatistics().getTotalGamesPlayed());

        //mg.addAnnounce(martin, 30);
        //mg.addAnnounce(guillaume, 40);
        //assertEquals(30, martin.getTotalAnnounce());
        //assertEquals(40, guillaume.getTotalAnnounce());
        //assertEquals(15, martin.getAnnouncePerGame(), 0);
        //assertEquals(20, guillaume.getAnnouncePerGame(), 0);
        //assertEquals(140, mg.getTotalPoints());
    }

    @Test
    public void test() {
        Statistics statistics = guillaume.getStatistics();
    }

    @Test
    public void containsTest() {
        assertTrue(mg.contains(martin));
        assertTrue(mg.contains(guillaume));
    }

    @Test
    public void equalsTest() {
        Duo duoTest = new Duo(martin, guillaume, new Statistics(0, 0));
        assertEquals(mg, duoTest);
    }

    @Test
    public void hashCodeTest() {
        Duo duoTest = new Duo(martin, guillaume, new Statistics(0, 0));
        assertEquals(mg.hashCode(), duoTest.hashCode());
    }

    @Test
    public void arrayTest() {
        Player[] players = new Player[2];
        players[0] = martin;
        players[1] = guillaume;

        assertEquals(martin, players[0]);
        assertEquals(guillaume, players[1]);
        Arrays.sort(players, Comparator.comparing(Player::getUserName));
        assertEquals(guillaume, players[0]);
        assertEquals(martin, players[1]);

        Player luan = new Player("Luan", new Statistics(0, 0));
        Player luc = new Player("Luc", new Statistics(0, 0));

        players[0] = luc;
        players[1] = luan;

        assertEquals(luc, players[0]);
        assertEquals(luan, players[1]);
        Arrays.sort(players, Comparator.comparing(Player::getUserName));
        assertEquals(luan, players[0]);
        assertEquals(luc, players[1]);
    }
}