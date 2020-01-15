package controller;


import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_main.GUI;
import models.Player;

public class ChanceController {

    private int chanceCard;

    public void landOnChance(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {

        chanceCard = (int) (Math.random() * 0);

        switch (chanceCard) {
            case 0: moveFiveStepsForward(player, gui_player, gui, fields);
        }
    }

    private void moveFiveStepsForward(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {
        gui.displayChanceCard(player.getName() + ", trækker et chancekort og får 'ryk fem felter frem'");
        gui.showMessage("Aktivér chancekortet");
        fields[player.getLocation()].setCar(gui_player, false);
        int newLokation = (player.getLocation() + 5) % fields.length;
        player.setLocation(newLokation);
        fields[newLokation].setCar(gui_player, true);
    }
}
