package com.schneuwly.victor.chibraxamax.model;

/**
 * A game of Chibre
 *
 * @author Victor Schneuwly
 */
public class Game {
    public static int MAX_POINTS = 150;

    private final Duo[] duos;

    public Game(Duo duo1, Duo duo2) {
        duos = new Duo[2];
        duos[0] = duo1;
        duos[1] = duo2;
    }

    public boolean contains(Duo duo){
        return duo.equals(duos[0]) || duo.equals(duos[1]);

    }

    public boolean containsPlayer(Player player){
        for (Duo duo : duos) {
            if (duo.contains(player))
                return true;
        }

        return false;

    }

    public void addPoints(Duo duo, int points) throws IllegalArgumentException {
        if (duo.equals(duos[0])) {
            duos[0].addPoints(points);
            duos[1].addPoints(MAX_POINTS - points);
        } else if (duo.equals(duos[1])) {
            duos[1].addPoints(points);
            duos[0].addPoints(MAX_POINTS - points);
        } else {
            throw new IllegalArgumentException("Duo not in the game.");
        }
    }

    public void addAnnounce(Player player, int announce) throws IllegalArgumentException {
        if (!(containsPlayer(player)))
            throw new IllegalArgumentException("Duo not in the game.");

        duoOfPlayer(player).addAnnounce(player, announce);

    }

    private Duo duoOfPlayer(Player player) throws IllegalArgumentException{
        for (Duo duo : duos) {
            if (duo.contains(player)) {
                return duo;
            }
        }

        throw new IllegalArgumentException("Player not in the game.");
    }



}
