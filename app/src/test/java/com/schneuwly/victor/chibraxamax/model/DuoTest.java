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

    @Test
    public void referenceTest() {
        Player martin = new Player("Martin", new Record(0, 0));
        Player guillaume = new Player("Guillaume", new Record(0, 0));
        Duo mg = new Duo(martin, guillaume, new Record(0, 0));

        mg.addWin();
        mg.addLoss();
        mg.addPoints(70);

        assertEquals(70, mg.getTotalPoints());
        assertEquals(1, martin.getRecord().getWins());
        assertEquals(1, martin.getRecord().getLosses());
        assertEquals(2, martin.getRecord().getTotalGamesPlayed());
        assertEquals(1, guillaume.getRecord().getWins());
        assertEquals(1, guillaume.getRecord().getLosses());
        assertEquals(2, guillaume.getRecord().getTotalGamesPlayed());

        //mg.addAnnounce(martin, 30);
        //mg.addAnnounce(guillaume, 40);
        //assertEquals(30, martin.getTotalAnnounce());
        //assertEquals(40, guillaume.getTotalAnnounce());
        //assertEquals(15, martin.getAnnouncePerGame(), 0);
        //assertEquals(20, guillaume.getAnnouncePerGame(), 0);
        //assertEquals(140, mg.getTotalPoints());
    }

    @Test
    public void containsTest() {
        Player martin = new Player("Martin", new Record(0, 0));
        Player guillaume = new Player("Guillaume", new Record(0, 0));
        Duo mg = new Duo(martin, guillaume, new Record(0, 0));

        assertTrue(mg.contains(martin));
        assertTrue(mg.contains(guillaume));
    }

    @Test
    public void arrayTest() {
        Player martin = new Player("Martin", new Record(0, 0));
        Player guillaume = new Player("Guillaume", new Record(0, 0));
        Duo mg = new Duo(martin, guillaume, new Record(0, 0));

        Player[] players = new Player[2];
        players[0] = martin;
        players[1] = guillaume;

        assertEquals(martin, players[0]);
        assertEquals(guillaume, players[1]);
        Arrays.sort(players, Comparator.comparing(Player::getUserName));
        assertEquals(guillaume, players[0]);
        assertEquals(martin, players[1]);

        Player luan = new Player("Luan", new Record(0, 0));
        Player luc = new Player("Luc", new Record(0, 0));

        players[0] = luc;
        players[1] = luan;

        assertEquals(luc, players[0]);
        assertEquals(luan, players[1]);
        Arrays.sort(players, Comparator.comparing(Player::getUserName));
        assertEquals(luan, players[0]);
        assertEquals(luc, players[1]);
    }

    @Test
    public void displayTest() {
        Player martin = new Player("Martin", new Record(0, 0));
        Player guillaume = new Player("Guillaume", new Record(0, 0));
        Duo mg = new Duo(martin, guillaume, new Record(0, 0));

        mg.addPoints(174);

        assertDisplay(mg.getPointsDisplay(), 1,1,1,4);

        mg.addPoints(20);
        mg.addPoints(50);
        mg.addPoints(100);

        assertDisplay(mg.getPointsDisplay(), 2,2,2,4);

        mg.addPoints(17);

        assertDisplay(mg.getPointsDisplay(), 3,2,2,1);

        mg.addPoints(4);

        assertDisplay(mg.getPointsDisplay(), 3,2,2,5);

        mg.addPoints(50);
        mg.addPoints(50);

        assertDisplay(mg.getPointsDisplay(), 3,4,2,5);

        mg.addPoints(100);

        assertDisplay(mg.getPointsDisplay(), 3,4,3,5);

        mg.addPoints(115);

        assertDisplay(mg.getPointsDisplay(), 4,4,4,0);

    }

    private void assertDisplay(Duo.PointsDisplay pointsDisplay, int nb20, int nb50, int nb100, int rest){
        assertEquals(nb20, pointsDisplay.getNb20());
        assertEquals(nb50, pointsDisplay.getNb50());
        assertEquals(nb100, pointsDisplay.getNb100());
        assertEquals(rest, pointsDisplay.getRest());
    }
}