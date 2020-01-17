package controller;

import fields.Field;
import fields.Street;
import gui_fields.GUI_Field;
import gui_fields.GUI_Player;
import gui_fields.GUI_Street;
import gui_main.GUI;
import models.Board;
import models.Player;

public class BuyHouseController {

    private Board board = new Board();


    /*

    public void buyHouse(Player player, GUI_Player gui_player, GUI gui, GUI_Field[] fields) {

        Field field = board.getField(player.getLocation());

        Street streetField = ((Street) field);

        switch (gui.getUserButtonPressed("Vil du købe grund?", "Ja", "Nej")) {

            case "Ja":
                GUI_Street gui_street = (GUI_Street) fields[field.getId()];
                //gui_street.setHouses(4);
                gui_street.setHotel(true);
                streetField.setRent(streetField.getRent() * 2);
                gui.displayChanceCard("Den nye leje er nu på " + streetField.getRent());
                break;
            default:
                gui.displayChanceCard(player.getName() + ", køber ikke grund");
        }
    }

     */

    public void test(GUI_Player p, GUI gui, GUI_Field[] fields) {


        Player player = new Player();

        switch (gui.getUserButtonPressed("Vil du afslutte din tur?", "Afslut tur", "Køb grund")) {

            case "Køb grund":
                Field field = board.getField(player.getLocation());
                Street streetField = ((Street) field);
                GUI_Street gui_street = (GUI_Street) fields[field.getId()];

                switch (gui.getUserSelection("Hvilket felt vil du købe grund til?", "Ja")) {

                    case "Ja":
                        gui_street.setHouses(1);
                }
        }
    }

         /*

    public String ownedFields(GUI_Field[] field) {

        Player player = new Player();

        field = new GUI_Field[28];
        Field f = board.getField(player.getLocation());



        for (int i = 0; i < field.length; i++) {

            if(f instanceof Street) {
               int k = ((Street) f).getOwner().getId();


            }

        }

          */


    }
































