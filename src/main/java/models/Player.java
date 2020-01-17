package models;


public class Player {

    private int id;
    private String name;
    private int location;
    private Account account;
    private Dice dice = new Dice();
    private int dice1, dice2;
    private int diceSum;
    private int amountOfShipping = -1;
    private int amountOfBrewery = -1;
    private boolean lost = false;
    private int jailTime=0;

    public Player() {
        account = new Account(5000);
    }

    public String getName() {
        return name;
    }

    public void diceRoll() {
        dice1 = dice.roll();
        dice2 = dice.roll();
        diceSum = dice1 + dice2;
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

    public int getDice1() {
        return dice1;
    }

    public int getDice2() {
        return dice2;
    }

    public int getDiceSum() {
        return diceSum;
    }

    public int getId() {
        return id;
    }

    public void isLost(boolean lost) {
        this.lost = lost;
    }

    public int getLost(){ if (lost){return 1;}else{ return 0;}}

    public void setId(int id) {
        this.id = id;
    }

    public void adShipping(){
       amountOfShipping +=1;
    }

    public int getAmountOfShipping(){
        return amountOfShipping;
    }

    public void adBrewery(){amountOfBrewery+=1;}

    public int getAmountOfBrewery() {
        return amountOfBrewery;
    }

    public void setJail(int jailTime) {
        this.jailTime = jailTime;
    }

    public int getJail(){ return jailTime;}

    public void passTime(){
        jailTime-=1;
    }

}















