package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {

    @Test
    public void roll() {
        Dice dice = new Dice();

        for (int i = 0; i < 1000; i++) {
            dice.roll();
            assertEquals(dice.roll(), dice.getFaceValue());
            System.out.println(dice.getFaceValue());
        }
    }
}



