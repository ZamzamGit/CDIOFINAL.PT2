package models;

public class Dice {

    private final int MAX = 6;
    private int faceValue;

    public Dice() {
    }

    public int roll() {
        faceValue = (int) (Math.random() * MAX) + 1;
        return faceValue;
    }

    public int getFaceValue() {
        return faceValue;
    }
}
