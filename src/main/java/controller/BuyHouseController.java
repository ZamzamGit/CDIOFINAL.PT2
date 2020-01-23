package controller;

import fields.Field;
import fields.Shipping;
import fields.Street;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import models.Player;

public class BuyHouseController {


    public void buyHouse(Player player, GUI_Player gui_player, GUI gui, Field[] fields, GUI_Field[] gui_fields) {
        String[] options = getFieldsOwnedByPlayer(player, fields);
        if (options.length == 0)
            return;

        switch (gui.getUserButtonPressed(player.getName()+", vil du afslutte din tur?", "Afslut tur", "Køb hus")) {

            case "Køb hus":

                String fieldName = gui.getUserSelection(player.getName()+", hvilken grund vil du købe et hus til", options);
                for (Field field : fields) {
                    boolean thisIsTheField = fieldName.equals(field.getName());
                    if (thisIsTheField) {

                        Street street = (Street) field;
                        GUI_Street gui_street = (GUI_Street) gui_fields[street.getId()];
                        if (street.getHouse() < 5) {
                            gui.showMessage(player.getName() + " køber huset for " + street.getHousePrice());
                            street.setHouse(street.getHouse() + 1);

                            if (street.getHouse() < 5) {
                                gui_street.setHouses(street.getHouse());
                                player.getAccount().withdraw(street.getHousePrice());
                                gui_player.setBalance(player.getAccount().getBalance());
                                street.setRent(street.getHouse());
                                gui.displayChanceCard(street.getName() + " husleje er nu på " + street.getRent());
                            } else if (street.getHouse() == 5) {
                                player.getAccount().withdraw(street.getHousePrice());
                                gui_player.setBalance(player.getAccount().getBalance());
                                street.setRent(street.getHouse());
                                gui_street.setHotel(true);
                                gui.displayChanceCard(street.getName() + " husleje er nu på " + street.getRent());
                            }
                            } else {
                                gui.displayChanceCard(player.getName() + ", du kan ikke købe flere ejendomme på dette felt");
                                buyHouse(player, gui_player, gui, fields, gui_fields);
                            }
                    }
                }
            default:

        }
    }

    private String[] getFieldsOwnedByPlayer(Player player, Field[] fields) {
                        boolean hasOwner;
                        boolean playerOwnsField;

                        int count = 0;
                        for (Field field : fields) {
                            if (field instanceof Street) {
                                Street street = (Street) field;
                                hasOwner = street.getOwner() != null;
                                if (hasOwner) {
                                    playerOwnsField = street.getOwner().equals(player);
                                    if (playerOwnsField)
                                        count++;
                                }
                            }
                        }

                        String[] options = new String[count];
                        int nextOption = 0;

                        for (Field field : fields) {
                            if (field instanceof Street) {
                                Street street = (Street) field;
                                hasOwner = street.getOwner() != null;
                                if (hasOwner) {
                                    playerOwnsField = street.getOwner().equals(player);
                                    if (playerOwnsField) {
                                        String fieldName = street.getName();
                                        options[nextOption++] = fieldName;
                                    }
                }
            }
        }
        return options;
    }
}
