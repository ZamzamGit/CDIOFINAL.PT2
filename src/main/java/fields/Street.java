package fields;

import gui_fields.GUI_Player;
import models.Player;

import java.awt.*;

public class Street extends Field {

    private int value;
    private Player owner;
    private boolean owned = false;
    private int house = 0;
    private int housePrice;
    private int houseRent[] = new int [6];
    private int rent;
    int number=0;
    public Street(int id, String name, int value, int rent0, int rent1, int rent2, int rent3, int rent4, int rent5, int housePrice, Color color) {
        super(id, name, color);

        this.value = value;
        this.houseRent[0] = rent0;
        this.houseRent[1] = rent1;
        this.houseRent[2] = rent2;
        this.houseRent[3] = rent3;
        this.houseRent[4] = rent4;
        this.houseRent[5] = rent5;
        this.housePrice = housePrice;
        this.rent=houseRent[number];
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setOwner(Player owner) {
        this.owner = owner;

    }

    public Player getOwner() {
        return owner;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }

    public boolean isOwned() {
        return this.owned;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getHousePrice() {
        System.out.println(housePrice);
        return housePrice;
    }

    public void setRent(int number) {
        this.rent = houseRent[number];



    }

    public int getRent() {
        return rent;
    }
}






