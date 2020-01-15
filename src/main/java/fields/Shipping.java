package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Shipping extends Field {

    private int value;

    public Shipping(String name, int value, Color color) {
        super(name, color);
        this.value = value;
    }
}