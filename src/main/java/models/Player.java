package models;


public class Player {

    private int id;
    private String name;
    private int location;
    private Account account;
    private Dice dice = new Dice();
    private int terning1, terning2;
    private int terningSum;
    private int amountOfShipping = -1;
    private int amountOfBrewery = -1;
    private boolean lost = false;

    public Player() {
        account = new Account(300);
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

    public void isLost(boolean lost) {
        this.lost = lost;
    }

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

    public int getLost(){
    if (lost==true){
        return 1;
    }else{
        return 0;
    }
}
}















