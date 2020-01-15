package controller;

import fields.*;
import gui_fields.*;
import gui_main.GUI;
import models.Board;
import models.Player;
import java.awt.*;
import java.util.Locale;

public class GameLogic {

    private GUI gui;
    private GUI_Player[] players;
    private GUI_Car[] cars;
    private GUI_Field[] fields;
    private Board board = new Board();
    private int newLocation;
    boolean passedStart = false;

    public GameLogic() {

        String[][] fieldText = board.getFieldText();
        fields = new GUI_Field[fieldText.length];

        for (int i = 0; i < fieldText.length; i++) {
            fields[i] = new GUI_Street();
            fields[i].setTitle(fieldText[i][0]);
            fields[i].setSubText(fieldText[i][1]);

            Color color = board.getField(i).getColor();
            fields[i].setBackGroundColor(color);

            GUI_Jail jail = new GUI_Jail();
            GUI_Jail jail2 = new GUI_Jail();
            fields[10] = jail;
            jail.setSubText("Fængsel");
            fields[30] = jail2;
            jail2.setSubText("Gå i fængsel");

            GUI_Start start = new GUI_Start("Start", "Modtag 200", "", Color.RED, Color.BLACK);
            fields[0] = start;

            GUI_Chance chance = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[2] = chance;
            GUI_Chance chance2 = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[7] = chance2;
            GUI_Chance chance3 = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[17] = chance3;
            GUI_Chance chance4 = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[22] = chance4;
            GUI_Chance chance5 = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[33] = chance5;
            GUI_Chance chance6 = new GUI_Chance("?", "Prøv lykken", "", Color.BLACK, Color.WHITE);
            fields[36] = chance6;

            GUI_Brewery tuborg = new GUI_Brewery("default", "Tuborg", "150", "", "", Color.BLACK, Color.WHITE);
            GUI_Brewery carlsberg = new GUI_Brewery("default", "Carlsberg", "150", "", "", Color.BLACK, Color.WHITE);
            fields[12] = tuborg;
            fields[28] = carlsberg;

            GUI_Shipping ship = new GUI_Shipping("default", "Øresund", "200", "", "", Color.WHITE, Color.BLACK);
            fields[5] = ship;
            GUI_Shipping ship2 = new GUI_Shipping("default", "D.F.D.S.", "200", "", "", Color.WHITE, Color.BLACK);
            fields[15] = ship2;
            GUI_Shipping ship3 = new GUI_Shipping("default", "Ø.S.", "200", "", "", Color.WHITE, Color.BLACK);
            fields[25] = ship3;
            GUI_Shipping ship4 = new GUI_Shipping("default", "Bornholm", "200", "", "", Color.WHITE, Color.BLACK);
            fields[35] = ship4;

            GUI_Refuge refuge = new GUI_Refuge("default", "", "Helle", "", Color.WHITE, Color.BLACK);
            fields[20] = refuge;
        }
        gui = new GUI(fields);
    }

    public void showMessage(String msg) {
        gui.showMessage(msg);
    }

    public void setDice(int terning1, int terning2) {
        gui.setDice(terning1, terning2);
    }

    public String getUserButtonPressed(String msg, String[] options) {
        return gui.getUserButtonPressed(msg, options);
    }

    public void displayChanceCard(String msg) {
        gui.displayChanceCard(msg);
    }

    public String getUserString(String msg) {
        return gui.getUserString(msg);
    }


    public void addPlayers(Player[] player) {

        players = new GUI_Player[player.length];
        cars = new GUI_Car[player.length];

        for (int i = 0; i < player.length; i++) {

            cars[i] = new GUI_Car();

            switch (i) {
                case 0:
                    cars[i].setPrimaryColor(Color.BLUE);
                    break;
                case 1:
                    cars[i].setPrimaryColor(Color.RED);
                    break;
                case 2:
                    cars[i].setPrimaryColor(Color.GREEN);
                    break;
                case 3:
                    cars[i].setPrimaryColor(Color.YELLOW);
                    break;
                case 4:
                    cars[i].setPrimaryColor(Color.WHITE);
                    break;
                default:
                    cars[i].setPrimaryColor(Color.BLACK);

            }
            players[i] = new GUI_Player(player[i].getName(), player[i].getAccount().getBalance(), cars[i]);
            gui.addPlayer(players[i]);
        }

        for (int i = 0; i < player.length; i++) {
            fields[0].setCar(players[i], true);
        }
    }

    public void move(Player[] player) {

        while (true) {

            for (int i = 0; i < player.length; i++) {
                gui.getUserButtonPressed(player[i].getName() + ",  slå med terning", "OK");

                player[i].diceRoll();

                gui.setDice(player[i].getTerning1(), player[i].getTerning2());

                newLocation = (player[i].getLocation() + player[i].getTerningSum());

                if (newLocation > fields.length) {
                    passedStart = true;
                } else {
                    passedStart = false;

                }
                newLocation = newLocation % fields.length;


                fields[player[i].getLocation()].setCar(players[i], false);

                player[i].setLocation(newLocation);

                fields[newLocation].setCar(players[i], true);

                landOnField(player[i], players[i]);

                if (passedStart == true) {
                    player[i].setLocation(newLocation);
                    player[i].getAccount().deposit(200);
                    players[i].setBalance(player[i].getAccount().getBalance());
                } else {

                    player[i].setLocation(newLocation);
                }
            }
        }
    }

    public void landOnField(Player player, GUI_Player gui_player) {

        Field field = board.getField(player.getLocation());

        if (field instanceof Street) {
            Street streetField = ((Street) field);
            if (!(streetField.isOwned())) {

                switch (gui.getUserButtonPressed(player.getName() + ", vil du købe feltet for " + streetField.getValue() + " med en" +
                        " leje på " + streetField.getRent() + "?", "Ja", "Nej")) {

                    case "Ja":
                        streetField.setOwner(player);
                        streetField.setOwned(true);
                        gui.displayChanceCard(player.getName() + " køber feltet for " + streetField.getValue());
                        player.getAccount().withdraw(streetField.getValue());
                        gui_player.setBalance(player.getAccount().getBalance());
                        break;
                    default:
                        displayChanceCard("Du køber ikke feltet");
                }
            } else if (player == streetField.getOwner()) {
                displayChanceCard(player.getName() + ", du er landet på dit eget felt");

            } else {
                gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + streetField.getRent() + " til " + streetField.getOwner().getName());
                player.getAccount().withdraw(streetField.getRent());
                gui_player.setBalance(player.getAccount().getBalance());

                streetField.getOwner().getAccount().deposit(streetField.getRent());
                players[streetField.getOwner().getId()].setBalance(streetField.getOwner().getAccount().getBalance());
            }

        } else if (field instanceof GoToJail) {
            gui.displayChanceCard(player.getName() + " går i fængsel");
            fields[player.getLocation()].setCar(gui_player, false);
            newLocation = (player.getLocation() + 20) % fields.length;
            fields[newLocation].setCar(gui_player, true);

        } else if(field instanceof Tax) {
            Tax taxField = ((Tax) field);
            gui.displayChanceCard(player.getName() + " du skal betale " + taxField.getTax() + " i skat");
            player.getAccount().withdraw(taxField.getTax());
            gui_player.setBalance(player.getAccount().getBalance());
        } else if (field instanceof Chance) {

        }
    }
}



















