package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class Tax extends Field {

    private int value;

    public Tax(String name, int value,  Color color) {
        super(name, color);
        this.value = value;
    }
    @Override
    public int getValue() {
        return value;
    }


    public void landOnTax (Player player, GameLogic gui) {
        gui.showMessage(player.getName() + " du skal betale " + value + " i skat");
        player.getAccount().withdraw(value);

    }
}
