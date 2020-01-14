package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Brewery extends Field {

    private int value;

    public Brewery(String name, int value, Color color) {
        super(name, color);
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
