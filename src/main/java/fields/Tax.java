package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Tax extends Field {

    private int tax;

    public Tax(int id, String name, int tax,  Color color) {
        super(id, name, color);
        this.tax = tax;
    }

    public int getTax() {
        return tax;
    }
}
