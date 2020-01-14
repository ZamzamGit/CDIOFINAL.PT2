package fields;

import gui_fields.GUI_Player;
import models.Player;

import java.awt.*;

public class Street extends Field {

    private int value;
    private int rent;
    private GUI_Player owner;
    private boolean owned = false;

    public Street(String name, int value, int rent, Color color) {
        super(name, color);
        this.value = value;
        this.rent = rent;
    }

    @Override
    public int getValue() {
        return value;
    }

    public void setOwner(GUI_Player owner) {
        this.owner = owner;
    }

    @Override
    public GUI_Player getOwner() {
        return owner;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwned() {
        owned = true;
    }

    @Override
    public int getRent() {
        return rent;
    }
}






