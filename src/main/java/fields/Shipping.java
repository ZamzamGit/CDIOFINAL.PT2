package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Shipping extends Field {

    private int value;
    private int rent;
    private Player owner;
    private boolean owned = false;


    public Shipping(String name, int value, int rent, Color color) {
        super(name, color);
        this.value = value;
        this.rent = rent;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setOwner(Player owner) {
        this.owner = owner;




    }

    public int getRent() {
        return rent;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }


}