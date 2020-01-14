package fields;

import controller.GameLogic;
import models.Player;

import java.awt.*;

public class GoToJail extends Field {

    public GoToJail(String name, Color color) {
        super(name, color);
    }

    public void landOnGoToJail (Player player) {

        player.getAccount().deposit(0);


    }
}
