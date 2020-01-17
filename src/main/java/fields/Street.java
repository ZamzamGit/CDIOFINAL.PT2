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
    private int rent, rent2, rent3, rent4, rent5, rent6;

    public Street(int id, String name, int value, int rent, int rent2, int rent3, int rent4, int rent5, int rent6, int housePrice, Color color) {
        super(id, name, color);
        this.value = value;
        this.rent = rent;
        this.rent2 = rent2;
        this.rent3 = rent3;
        this.rent4 = rent4;
        this.rent5 = rent5;
        this.rent6 = rent6;
        this.housePrice = housePrice;
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

    public int getRent() {
        return rent;
    }

    public int getRent2() {
        return rent2;
    }

    public int getRent3() {
        return rent3;
    }

    public int getRent4() {
        return rent4;
    }

    public int getRent5() {
        return rent5;
    }

    public int getRent6() {
        return rent6;
    }

    public int getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setRent(int rent) {
        this.rent = rent;
    }
}






