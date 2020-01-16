package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Brewery extends Field {

    private int value;
    private Player owner;
    private boolean owned = false;

    public Brewery(int id, String name, int value, Color color) {
        super(id, name, color);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isOwned() {
        return owned;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setOwned(boolean owned) {
        this.owned = owned;
    }
}
