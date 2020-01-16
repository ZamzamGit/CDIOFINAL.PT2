package fields;

import gui_fields.GUI_Player;
import models.Player;

import java.awt.*;

public class Street extends Field {

    private int value;
    private int rent;
    private Player owner;
    private boolean owned = false;

    public Street(int id, String name, int value, int rent, Color color) {
        super(id, name, color);
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

    public void setRent(int rent) {
        this.rent = rent;
    }
}








