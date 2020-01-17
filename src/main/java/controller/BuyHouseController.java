package controller;

import fields.Field;
import fields.Street;
import gui_fields.GUI_Field;
import gui_fields.GUI_Street;
import gui_main.GUI;
import models.Player;

public class BuyHouseController {

    public void buyHouse(Player player, GUI gui, Field[] fields, GUI_Field[] gui_fields) {
        String[] options = getFieldsOwnedByPlayer(player, fields);
        if(options.length == 0)
            return;

       switch (gui.getUserButtonPressed("Vil du afslutte din tur?", "Afslut tur", "Køb hus")) {

           case "Køb hus":

             String fieldName = gui.getUserSelection("Hvilken grund vil du købe et hus til", options);
             for(Field field : fields){
                 boolean thisIsTheField = fieldName.equals(field.getName());
                 if(thisIsTheField){

                     Street street =  (Street) field;
                     street.setHouse(street.getHouse()+1);
                     GUI_Street gui_street = (GUI_Street) gui_fields[street.getId()];
                     gui_street.setHouses(street.getHouse());

                     //TODO : Hotels are not handled atm

                 }
             }

           default:







       }


    }

    private String[] getFieldsOwnedByPlayer(Player player, Field[] fields) {
        //TODO: Make sure you cant build more than 5 houses
        int count = 0;
        for (Field field : fields) {
            if (field instanceof Street) {
                Street street = (Street) field;
                boolean hasOwner = street.getOwner() != null;
                if(hasOwner) {
                    boolean playerOwnsField = street.getOwner().equals(player);
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
                boolean hasOwner = street.getOwner() != null;
                if(hasOwner){

                      boolean playerOwnsField = street.getOwner().equals(player);
                      if (playerOwnsField) {
                          String fieldName = street.getName();
                          options[nextOption++] = fieldName;
                      }
                }
            }
        }
        return options;
    }

    public void test() {






    }



}
