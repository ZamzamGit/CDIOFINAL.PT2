package controller;

import models.Board;
import models.Player;

public class Monopoly {

    private GameLogic gui;
    private Player[] players;

    public Monopoly() {
        this.gui = new GameLogic();
    }

    public void setUp() {

        int antal = Integer.parseInt(gui.getUserButtonPressed("Hvor mange spillere?", new String[]{"2", "3", "4", "5", "6"}));

        players = new Player[antal];

        for (int i = 0; i < antal; i++) {
            String name = gui.getUserString("Spiller " + (i + 1) + ",  indtast dit navn");
            players[i] = new Player();
            players[i].setId(i);
            players[i].setName(name);
        }
        gui.addPlayers(players);
        gui.movePlayer(players);
    }
}














