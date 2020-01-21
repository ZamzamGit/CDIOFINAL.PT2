package controller;

import gui_fields.*;
import gui_main.GUI;
import models.Player;

import java.awt.*;

public class Monopoly {

    private GameLogic gameLogic;
    private Player[] players;

    public Monopoly() {
        gameLogic = new GameLogic();
    }

    public void game() {

        int antal = Integer.parseInt(gameLogic.getUserButtonPressed("Hvor mange spillere?", new String[]{"2", "3", "4", "5", "6"}));

        players = new Player[antal];

        for (int i = 0; i < antal; i++) {
            String name = gameLogic.getUserString("Spiller " + (i + 1) + ",  indtast dit navn");

            players[i] = new Player();
            players[i].setId(i);
            players[i].setName(name);
        }
        gameLogic.addPlayers(players);
        gameLogic.movePlayer(players);
    }
}














