package fields;

import controller.GameLogic;
import gui_fields.GUI_Player;
import models.Player;

import java.awt.*;

public class Field {

    private String name;
    private Color color;

    public Field(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getValue() {
        return 0;
    }

    public int getRent() {
        return 0;
    }

    public void setOwner(GUI_Player owner) {
    }

    public boolean isOwned() {
        return false;
    }

    public GUI_Player getOwner() {
        return null;
    }

    public void setOwned() {
    }
}

