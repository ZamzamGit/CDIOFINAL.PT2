package controller;


import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import models.Player;

public class ChanceController {


    public void moveFiveStepsForward(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Ryk fem felter frem");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        fields[player.getLocation()].setCar(gui_player, false);
        int newLokation = (player.getLocation() + 5) % fields.length;
        player.setLocation(newLokation);
        fields[newLokation].setCar(gui_player, true);
    }

    public void moveTwoStepsBack(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard(" Ryk tre felter tilbage");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        fields[player.getLocation()].setCar(gui_player, false);
        int newLokation = (player.getLocation() - 2) % fields.length;
        player.setLocation(newLokation);
        fields[newLokation].setCar(gui_player, true);
    }

    public void moveToStart(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Ryk frem til start");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        fields[player.getLocation()].setCar(gui_player, false);
        int newLokation = 0;
        player.setLocation(newLokation);
        fields[newLokation].setCar(gui_player, true);
    }

    public void parkingFine(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Du har fået en parkeringbøde, betal 20 kr.");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        player.getAccount().withdraw(20);
        gui_player.setBalance(player.getAccount().getBalance());
    }

    public void newTire(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Du har anskaffet et nyt dæk til din vogn. Indbetal 100 kr.");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        player.getAccount().withdraw(100);
        gui_player.setBalance(player.getAccount().getBalance());
    }

    public void recieveAmount(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Du har solgt din gamle klude. Modtag 20 kr.");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        player.getAccount().deposit(20);
        gui_player.setBalance(player.getAccount().getBalance());
    }

    public void moveToRådhuspladsen(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard("Tag ind på Rådhuspladsen");
        gui.getUserButtonPressed(player.getName(), "Aktivér chancekort");
        fields[player.getLocation()].setCar(gui_player, false);
        int newLokation = 39;
        player.setLocation(newLokation);
        fields[newLokation].setCar(gui_player, true);
    }
}
