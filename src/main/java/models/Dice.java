package models;

public class Dice {

    private final int MAX = 1;
    private int faceValue;

    public Dice() {
    }

    public int roll() {
        faceValue = (int) (Math.random() * MAX) + 1;
        return faceValue;
    }
}
