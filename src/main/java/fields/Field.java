package fields;

import controller.GameLogic;
import gui_fields.GUI_Player;
import models.Player;

import java.awt.*;

public class Field {

    private String name;
    private Color color;
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

