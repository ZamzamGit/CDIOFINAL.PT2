package models;


public class Player {

    private int id;
    private String name;
    private int location;
    private Account account;
    private Dice dice = new Dice();
    private int terning1, terning2;
    private int terningSum;

    public Player() {
        account = new Account(1500);
    }

    public String getName() {
        return name;
    }

    public void diceRoll() {
        terning1 = dice.roll();
        terning2 = dice.roll();
        terningSum = terning1 + terning2;
    }

    public int getLocation() {
        return location;
    }

    public Account getAccount() {
        return account;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getTerning1() {
        return terning1;
    }

    public int getTerning2() {
        return terning2;
    }

    public int getTerningSum() {
        return terningSum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}















