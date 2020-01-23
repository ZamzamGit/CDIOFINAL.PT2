package controller;
import fields.*;
import gui_fields.*;
import gui_main.GUI;
import models.Board;
import models.ChanceCard;
import models.Player;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class GameLogic {

    private GUI gui;
    private GUI_Player[] players;
    private GUI_Car[] cars;
    private GUI_Field[] fields;
    private Board board = new Board();
    private int newLocation;
    private boolean passedStart = false;
    private ChanceCard chanceCard = new ChanceCard();
    private boolean gameOn = true;
    private int specialTurn = 0;
    private int combo = 0;
    private BuyHouseController b = new BuyHouseController();
    private int sleepTime = 400;

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

    public void movePlayer(Player[] player) throws InterruptedException {

        while (gameOn) {

            for (int i = 0; i < player.length; i++) {

                if (player[i].getLost() == false && gameOn) {

                    if (player[i].getJail() > 0) {

                        switch (gui.getUserButtonPressed(player[i].getName()+", vil du rulle to ens eller betale 50 for at komme ud?", "Rulle", "Betal 50")) {

                            case "Rulle":

                                player[i].diceRoll();
                                gui.setDice(player[i].getDice1(), player[i].getDice2());

                                if (player[i].getDice1() == (player[i].getDice2())) {
                                    player[i].setJail(0);
                                    displayChanceCard("Du er slået 2 ens og er derfor fri");
                                    specialTurn = i;
                                    checkIfDoubleDice(player);
                                }else{
                                    displayChanceCard("Du har ikke rullet 2 ens og skal derfor vente");
                                    player[i].passTime();

                                }
                              break;
                            default:
                                displayChanceCard("Du betaler 50 for at komme ud");
                                player[i].getAccount().withdraw(50);
                                players[i].setBalance(player[i].getAccount().getBalance());
                                player[i].setJail(0);

                        }
                    }else {
                        gui.getUserButtonPressed(player[i].getName() + ",  slå med terningerne", "OK");

                    player[i].diceRoll();

                    gui.setDice(player[i].getDice1(), player[i].getDice2());

                    for (int j = 0;j <player[i].getDiceSum();j++ ) {


                        newLocation = (player[i].getLocation() + 1);
                        Thread.sleep(sleepTime);


                        if (newLocation > fields.length) {
                            passedStart = true;
                        }
                        newLocation = newLocation % fields.length;

                        fields[player[i].getLocation()].setCar(players[i], false);

                        player[i].setLocation(newLocation);

                        fields[newLocation].setCar(players[i], true);
                    }
                    landOnField(player[i], players[i]);

                    b.buyHouse(player[i], players[i], gui, board.getField(), fields);

                    if (passedStart) {
                        player[i].getAccount().deposit(200);
                        players[i].setBalance(player[i].getAccount().getBalance());


                    }
                    if (player[i].getAccount().getBalance() < 0) {
                        fields[player[i].getLocation()].setCar(players[i], false);
                        player[i].isLost(true);
                        gui.displayChanceCard(player[i].getName() + ", er ude af spillet");


                    }
                    if (player[i].getDice1() == player[i].getDice2()) {
                        specialTurn = i;
                        checkIfDoubleDice(player);
                    }
                }
                checkIfGameOn(player);
            }
            }
        }
    }

    public void landOnField(Player player, GUI_Player gui_player) {

        Field field = board.getField(player.getLocation());

        if (field instanceof Street) {
            Street streetField = (Street) field;
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
            player.setJail(3);
            gui.displayChanceCard(player.getName() + " går i fængsel");
            fields[player.getLocation()].setCar(gui_player, false);
            player.setLocation(10);
            fields[player.getLocation()].setCar(gui_player, true);


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

            Shipping shipField = (Shipping) field;

            if (!shipField.isOwned()) {

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


                }
            } else if (player == shipField.getOwner()) {
                displayChanceCard(player.getName() + ", du er landet på dit eget felt");

            } else {
                if (shipField.getOwner().getAccount().getBalance() > 0) {
                    double shippingRent = shipField.getRent()
                            * Math.pow(2, shipField.getOwner().getAmountOfShipping());

                    gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + (int)shippingRent + " til " + shipField.getOwner().getName());


                    player.getAccount().withdraw((int) shippingRent);
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
                    this.chanceCard.moveFiveStepsForward(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 1:
                    this.chanceCard.moveTwoStepsBack(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 2:
                    this.chanceCard.moveToStart(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
                    break;
                case 3:
                    this.chanceCard.parkingFine(player, gui_player, gui, fields);
                    break;
                case 4:
                    this.chanceCard.newTire(player, gui_player, gui, fields);
                    break;
                case 5:
                    this.chanceCard.recieveAmount(player, gui_player, gui, fields);
                    break;
                case 6:
                    this.chanceCard.moveToRådhuspladsen(player, gui_player, gui, fields);
                    landOnField(player, gui_player);
            }
        } else if (field instanceof Jail) {
            gui.displayChanceCard(player.getName() + ", du er på besøg i fængslet");


            } else if (field instanceof Brewery) {
                Brewery breweryField = (Brewery) field;



                gui.displayChanceCard(player.getName() + " lander på et ledig felt");

            if (!breweryField.isOwned()) {

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
                int breweryRent = player.getDiceSum() * (4 + 6 * breweryField.getOwner().getAmountOfBrewery());

                if (breweryField.getOwner().getAccount().getBalance() > 0) {
                    gui.displayChanceCard(player.getName() + ", feltet er desværre ejet betal " + breweryRent + " til " + breweryField.getOwner().getName());
                    player.getAccount().withdraw(breweryRent);
                    gui_player.setBalance(player.getAccount().getBalance());

                    breweryField.getOwner().getAccount().deposit(breweryRent);
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

        int currentPlayers = players.length;


        for (int j = 0 ; j < player.length; j++){
            int lostCount=0;
            if(player[j].getLost() == true){
                lostCount +=1;
            }
            currentPlayers = currentPlayers - lostCount;


        if (currentPlayers == 1) {
            gameOn = false;
            for (int i = 0; i < player.length; i++) {

                if (player[i].getLost() == false) {
                    gui.displayChanceCard(player[i].getName() + " har vundet, tillykke med det. HUSK AT FØLGE SID ALI MOUNIB PÅ INSTAGRAM @SIDALI");
                }
            }

        }

    }

    public void checkIfDoubleDice(Player[] player) throws InterruptedException {

        combo += 1;

        if (player[specialTurn].getLost() == false && gameOn) {
            if (combo < 3) {
                gui.displayChanceCard(player[specialTurn].getName() + ", du har slået double og må derfor rulle igen");
                gui.getUserButtonPressed(player[specialTurn].getName() + ", slå med terningerne igen", "OK");

                player[specialTurn].diceRoll();

                gui.setDice(player[specialTurn].getDice1(), player[specialTurn].getDice2());

                newLocation = (player[specialTurn].getLocation() + player[specialTurn].getDiceSum());
                for (int j = 0;j <player[specialTurn].getDiceSum();j++ ) {


                    newLocation = (player[specialTurn].getLocation() + 1);
                    Thread.sleep(sleepTime);

                    if (newLocation > fields.length) {
                        passedStart = true;

                    }
                    newLocation = newLocation % fields.length;

                    fields[player[specialTurn].getLocation()].setCar(players[specialTurn], false);

                    player[specialTurn].setLocation(newLocation);

                    fields[newLocation].setCar(players[specialTurn], true);
                }
                landOnField(player[specialTurn], players[specialTurn]);

                if (passedStart) {
                    player[specialTurn].getAccount().deposit(200);
                    players[specialTurn].setBalance(player[specialTurn].getAccount().getBalance());

                }
                if (player[specialTurn].getAccount().getBalance() < 0) {
                    fields[player[specialTurn].getLocation()].setCar(players[specialTurn], false);
                    player[specialTurn].isLost(true);
                    gui.displayChanceCard(player[specialTurn].getName() + ", er ude af spillet");
                }
                if (player[specialTurn].getDice1() == player[specialTurn].getDice2()) {
                    checkIfDoubleDice(player);
                }else{
                    combo = 0;
                }
            } else {

                player[specialTurn].setLocation(30);

                fields[newLocation].setCar(players[specialTurn], false);

                landOnField(player[specialTurn], players[specialTurn]);

                gui.displayChanceCard(player[specialTurn].getName()+ ", du er blevet fanget i færdsel overskridelse og bliver derfor taget til fængsel");

                combo = 0;
            }
        }
    }
}






















