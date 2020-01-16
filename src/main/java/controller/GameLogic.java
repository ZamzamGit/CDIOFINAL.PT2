package controller;

import fields.*;
import gui_fields.*;
import gui_main.GUI;
import models.Board;
import models.Player;
import java.awt.*;

public class GameLogic {

    private GUI gui;
    private GUI_Player[] players;
    private GUI_Car[] cars;
    private GUI_Field[] fields;
    private Board board = new Board();
    private int newLocation;
    boolean passedStart = false;
    private ChanceController chanceController = new ChanceController();
    private boolean gameOn = true;
    private int currentPlayers;
    private int rollingDouble = 0;
    private int combo = 0;

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

            GUI_Tax tax = new GUI_Tax("Skat", "Betal 200", "", Color.LIGHT_GRAY, Color.BLACK);
            GUI_Tax tax2 = new GUI_Tax("Skat", "Betal 100", "", Color.LIGHT_GRAY, Color.BLACK);
            fields[4] = tax;
            fields[38] = tax2;
        }
        gui = new GUI(fields);
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

    public void movePlayer(Player[] player) {


        while (gameOn) {

            for (int i = 0; i < player.length; i++) {


                if (player[i].getLost() == 0 && gameOn == true) {

                    gui.getUserButtonPressed(player[i].getName() + ",  slå med terningerne", "OK");


                    player[i].diceRoll();

                    gui.setDice(player[i].getDice1(), player[i].getDice2());

                    newLocation = (player[i].getLocation() + player[i].getDiceSum());

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
                        player[i].getAccount().deposit(200);
                        players[i].setBalance(player[i].getAccount().getBalance());


                    }
                    if (player[i].getAccount().getBalance() < 0) {
                        fields[player[i].getLocation()].setCar(players[i], false);
                        player[i].isLost(true);
                        gui.displayChanceCard(player[i].getName() + ", er ude af spillet");


                    }
                    if (player[i].getDice1() == player[i].getDice2()) {
                        rollingDouble = i;
                        checkIfDoubleDice(player);
                    }
                }
                checkIfGameOn(player);
            }

        }
    }


    public void landOnField(Player player, GUI_Player gui_player) {

        Field field = board.getField(player.getLocation());

        if (field instanceof Street) {
            Street streetField = ((Street) field);
            if (!(streetField.isOwned())) {

                gui.displayChanceCard(player.getName() + " lander på et ledig felt");

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
                        displayChanceCard(player.getName() + " køber ikke feltet");
                }
            } else if (player == streetField.getOwner()) {
                displayChanceCard(player.getName() + ", du er landet på dit eget felt");

                switch (gui.getUserButtonPressed("Vil du købe grund?", "Ja", "Nej")) {

                    case "Ja":
                        GUI_Street gui_street = (GUI_Street) fields[field.getId()];
                        //gui_street.setHouses(4);
                        gui_street.setHotel(true);
                        streetField.setRent(streetField.getRent() * 2);
                        gui.displayChanceCard("Den nye leje er nu på " + streetField.getRent());
                        break;
                    default:
                        displayChanceCard(player.getName() + ", køber ikke grund");
                }
            } else {
                if (streetField.getOwner().getAccount().getBalance() > 0) {
                    gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + streetField.getRent() + " til " + streetField.getOwner().getName());
                    player.getAccount().withdraw(streetField.getRent());
                    gui_player.setBalance(player.getAccount().getBalance());

                    streetField.getOwner().getAccount().deposit(streetField.getRent());
                    players[streetField.getOwner().getId()].setBalance(streetField.getOwner().getAccount().getBalance());
                } else {
                    streetField.setOwner(null);
                    streetField.setOwned(false);
                    landOnField(player, gui_player);
                }
            }

        } else if (field instanceof GoToJail) {
            gui.displayChanceCard(player.getName() + " går i fængsel, og betaler 50 kr. for at komme ud næste runde");
            fields[player.getLocation()].setCar(gui_player, false);
            newLocation = 10;
            player.setLocation(newLocation);
            fields[newLocation].setCar(gui_player, true);
            player.getAccount().withdraw(50);
            gui_player.setBalance(player.getAccount().getBalance());

        } else if (field instanceof Tax) {
            Tax taxField = ((Tax) field);
            gui.displayChanceCard(player.getName() + " du skal betale " + taxField.getTax() + " i skat");
            player.getAccount().withdraw(taxField.getTax());
            gui_player.setBalance(player.getAccount().getBalance());

        } else if (field instanceof Start) {
            gui.displayChanceCard(player.getName() + " du er landet på start og modtager 200");
            player.getAccount().deposit(200);
            gui_player.setBalance(player.getAccount().getBalance());

        } else if (field instanceof Parking) {
            gui.displayChanceCard(player.getName() + ", tag dig en pause");

        } else if (field instanceof Shipping) {
            Shipping shipField = ((Shipping) field);
            if (!(shipField.isOwned())) {

                gui.displayChanceCard(player.getName() + " lander på et ledig felt");

                switch (gui.getUserButtonPressed(player.getName() + ", vil du købe feltet for " + shipField.getValue() + " med en" +
                        " leje på " + shipField.getRent() + "?", "Ja", "Nej")) {
                    case "Ja":
                        player.adShipping();
                        shipField.setOwner(player);
                        shipField.setOwned(true);
                        gui.displayChanceCard(player.getName() + " køber feltet for " + shipField.getValue());
                        player.getAccount().withdraw(shipField.getValue());
                        gui_player.setBalance(player.getAccount().getBalance());


                        break;
                    default:
                        displayChanceCard(player.getName() + " køber ikke feltet");

                        System.out.println();
                }
            } else if (player == shipField.getOwner()) {
                displayChanceCard(player.getName() + ", du er landet på dit eget felt");

            } else {
                if (shipField.getOwner().getAccount().getBalance() > 0) {
                    gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + shipField.getRent()
                            * Math.pow(2, shipField.getOwner().getAmountOfShipping()) + " til " + shipField.getOwner().getName());

                    player.getAccount().withdraw(shipField.getRent());
                    gui_player.setBalance(player.getAccount().getBalance());

                    shipField.getOwner().getAccount().deposit(shipField.getRent());
                    players[shipField.getOwner().getId()].setBalance(shipField.getOwner().getAccount().getBalance());
                } else {
                    shipField.setOwner(null);
                    shipField.setOwned(false);
                    landOnField(player, gui_player);
                }
            }

        } else if (field instanceof Chance) {

            int chanceCard = (int) (Math.random() * 7);

            switch (chanceCard) {
                case 0:
                    chanceController.moveFiveStepsForward(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 1:
                    chanceController.moveTwoStepsBack(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 2:
                    chanceController.moveToStart(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 3:
                    chanceController.parkingFine(player, gui_player, gui, fields);
                    break;
                case 4:
                    chanceController.newTire(player, gui_player, gui, fields);
                    break;
                case 5:
                    chanceController.recieveAmount(player, gui_player, gui, fields);
                    break;
                case 6:
                    chanceController.moveToRådhuspladsen(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
            }
        } else if (field instanceof Jail) {
            gui.displayChanceCard(player.getName() + ", du er på besøg i fængslet");


        } else if (field instanceof Brewery) {
            Brewery breweryField = ((Brewery) field);
            System.out.println(player.getDiceSum());
            // int rent = player.getTerningSum()*(4+6*breweryField.getOwner().getAmountOfBrewery());

            // int rent = player.getTerningSum()*(4+6*breweryField.getOwner().getAmountOfBrewery());
            gui.displayChanceCard(player.getName() + " lander på et ledig felt");

            if (!(breweryField.isOwned())) {

                switch (gui.getUserButtonPressed(player.getName() + ", vil du købe feltet for " + breweryField.getValue(), "Ja", "Nej")) {

                    case "Ja":
                        player.adBrewery();
                        breweryField.setOwner(player);
                        breweryField.setOwned(true);
                        gui.displayChanceCard(player.getName() + " køber feltet for " + breweryField.getValue());
                        player.getAccount().withdraw(breweryField.getValue());
                        gui_player.setBalance(player.getAccount().getBalance());
                        break;
                    default:
                        displayChanceCard(player.getName() + " køber ikke feltet");
                }
            } else if (player == breweryField.getOwner()) {
                displayChanceCard(player.getName() + ", du er landet på dit eget felt");

            } else {
                if (breweryField.getOwner().getAccount().getBalance() > 0) {
                    gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + player.getDiceSum() * (4 + 6 * breweryField.getOwner().getAmountOfBrewery()) + " til " + breweryField.getOwner().getName());
                    player.getAccount().withdraw(player.getDiceSum() * (4 + 6 * breweryField.getOwner().getAmountOfBrewery()));
                    gui_player.setBalance(player.getAccount().getBalance());

                    breweryField.getOwner().getAccount().deposit(player.getDiceSum() * (4 + 6 * breweryField.getOwner().getAmountOfBrewery()));
                    players[breweryField.getOwner().getId()].setBalance(breweryField.getOwner().getAccount().getBalance());
                } else {
                    breweryField.setOwner(null);
                    breweryField.setOwned(false);
                    landOnField(player, gui_player);
                }
            }
        }
    }

    public void checkIfGameOn(Player[] player) {

        currentPlayers = players.length;

        switch (player.length) {
            case 2:
                currentPlayers = currentPlayers - player[0].getLost() - player[1].getLost();
                break;
            case 3:
                currentPlayers = currentPlayers - player[0].getLost() - player[1].getLost() - player[2].getLost();
                break;
            case 4:
                currentPlayers = currentPlayers - player[0].getLost() - player[1].getLost() - player[2].getLost() - player[3].getLost();
                break;
            case 5:
                currentPlayers = currentPlayers - player[0].getLost() - player[1].getLost() - player[2].getLost() - player[3].getLost() - player[4].getLost();
                break;
            case 6:
                currentPlayers = currentPlayers - player[0].getLost() - player[1].getLost() - player[2].getLost() - player[3].getLost() - player[4].getLost() - player[5].getLost();
                break;
        }


        if (currentPlayers == 1) {
            gameOn = false;
            for (int i = 0; i < player.length; i++) {

                if (player[i].getLost() == 0) {
                    gui.displayChanceCard(player[i].getName() + " har vundet");
                }
            }

        }

    }

    public void checkIfDoubleDice(Player[] player) {


        combo += 1;

        if (player[rollingDouble].getLost() == 0 && gameOn == true) {
            if (combo < 3) {
                gui.displayChanceCard(player[rollingDouble]+ ", du har slået double og må derfor rulle igen");
                gui.getUserButtonPressed(player[rollingDouble].getName() + ", slå med terningerne igen", "OK");


                player[rollingDouble].diceRoll();

                gui.setDice(player[rollingDouble].getDice1(), player[rollingDouble].getDice2());

                newLocation = (player[rollingDouble].getLocation() + player[rollingDouble].getDiceSum());

                if (newLocation > fields.length) {
                    passedStart = true;
                } else {
                    passedStart = false;

                }
                newLocation = newLocation % fields.length;

                fields[player[rollingDouble].getLocation()].setCar(players[rollingDouble], false);

                player[rollingDouble].setLocation(newLocation);

                fields[newLocation].setCar(players[rollingDouble], true);

                landOnField(player[rollingDouble], players[rollingDouble]);

                if (passedStart == true) {
                    player[rollingDouble].getAccount().deposit(200);
                    players[rollingDouble].setBalance(player[rollingDouble].getAccount().getBalance());


                }
                if (player[rollingDouble].getAccount().getBalance() < 0) {
                    fields[player[rollingDouble].getLocation()].setCar(players[rollingDouble], false);
                    player[rollingDouble].isLost(true);
                    gui.displayChanceCard(player[rollingDouble].getName() + ", er ude af spillet");


                }
                if (player[rollingDouble].getDice1() == player[rollingDouble].getDice2()) {
                    checkIfDoubleDice(player);
                }
            } else {


                player[rollingDouble].setLocation(30);

                fields[newLocation].setCar(players[rollingDouble], false);

                landOnField(player[rollingDouble], players[rollingDouble]);

                gui.displayChanceCard(player[rollingDouble].getName()+", du er blevet fanget i færdsel overskridelse og bliver derfor taget til fængsel");
                combo = 0;
            }
        }
    }
}






















