package models;

import fields.*;

import java.awt.*;

public class Board {

    Field[] field = new Field[40];


    public Board() {
        field[0] = new Start(0,"Start", Color.RED);
        field[1] = new Street(1,"Rødovrevej", 60, 2, 10, 30, 90, 160, 250, 50, Color.CYAN);
        field[2] = new Chance(2,"Chance", Color.WHITE);
        field[3] = new Street(3,"Hvidovrevej", 60, 4, 20,60,180,320,540, 50, Color.CYAN);
        field[4] = new Tax(4,"Skat", 200, Color.LIGHT_GRAY);
        field[5] = new Shipping(5,"Øresund", 200, 25, Color.WHITE);
        field[6] = new Street(6,"Roskildevej", 100, 6, 30, 90,270,400,550, 50, Color.PINK);
        field[7] = new Chance(7,"Chance", Color.WHITE);
        field[8] = new Street(8,"Valby Langgade", 100, 6,30, 90,270,400,550, 50, Color.PINK);
        field[9] = new Street(9,"Allégade", 120, 8, 40, 100,300,450,600, 50,Color.PINK);
        field[10] = new Jail(10,"Fængsel", Color.WHITE);
        field[11] = new Street(11,"Frederiksberg Allé", 140, 10, 50, 150,450,625,750, 100, Color.GREEN);
        field[12] = new Brewery(12,"Tuborg", 150, Color.WHITE);
        field[13] = new Street(13,"Bülowsvej", 140, 10, 50, 150,450,625,750, 100, Color.GREEN);
        field[14] = new Street(14,"Gammel Kongevej", 160, 12, 50, 180,500,700,900, 100, Color.GREEN);
        field[15] = new Shipping(15,"D.F.D.S.", 200,25, Color.WHITE);
        field[16] = new Street(16,"Bernstorffsvej", 180, 14, 70, 200,550,750,950, 100, Color.ORANGE);
        field[17] = new Chance(17,"Chance", Color.WHITE);
        field[18] = new Street(18,"Hellerupvej", 180, 14, 70, 200,550,750,950, 100,Color.ORANGE);
        field[19] = new Street(19,"Strandvejen", 200, 16, 80, 220,600,800,1000, 100, Color.ORANGE);
        field[20] = new Parking(20,"Helle", Color.WHITE);
        field[21] = new Street(21,"Trianglen", 220, 18, 90, 250,700,875,1050, 150, Color.RED);
        field[22] = new Chance(22,"Chance", Color.WHITE);
        field[23] = new Street(23,"Østerbrogade", 220, 18,90, 250,700,875,1050, 150, Color.RED);
        field[24] = new Street(24,"Grønningen", 240, 20, 100, 300,750,925,1100, 150,Color.RED);
        field[25] = new Shipping(25,"Ø.S.", 200,25, Color.WHITE);
        field[26] = new Street(26,"Bredgade", 260, 22,110, 330,800,975,1150, 150, Color.BLUE);
        field[27] = new Street(27,"Kgs. Nytorv ", 260, 22,110, 330,800,975,1150, 150, Color.BLUE);
        field[28] = new Brewery(28,"Carlsberg", 150, Color.WHITE);
        field[29] = new Street(29,"Østergade", 280, 24, 120, 360,850,1025,1200, 150,Color.BLUE);
        field[30] = new GoToJail(30,"Gå i fængsel", Color.WHITE);
        field[31] = new Street(31,"Amagertorv", 300, 26, 130, 390,900,1100,1275, 200, Color.YELLOW);
        field[32] = new Street(32,"Vimmelskaftet", 300, 26,130, 390,900,1100,1275, 200, Color.YELLOW);
        field[33] = new Chance(33,"Chance", Color.WHITE);
        field[34] = new Street(34,"Nygade", 320, 28, 150, 450,1000,1200,1400, 200,Color.YELLOW);
        field[35] = new Shipping(35,"Bornholm", 200,25, Color.WHITE);
        field[36] = new Chance(36,"Chance", Color.WHITE);
        field[37] = new Street(37,"Frederiksberggade", 350, 35,175, 500,1100,1300,1500, 200, Color.MAGENTA);
        field[38] = new Tax(38,"Skat", 100, Color.LIGHT_GRAY);
        field[39] = new Street(39,"Rådhuspladsen", 400, 50,200, 600,1400,1700,2000, 200, Color.MAGENTA);
    }

    public int getSize() {
        return field.length;
    }

    public Field getField(int number) {
        return field[number];
    }

    public String[][] getFieldText() {
        String[][] fieldText = new String[getSize()][2];

        for (int i = 0; i < field.length; i++) {
            fieldText[i][0] = field[i].getName();
              fieldText[i][1] = "" + field[i].getValue();
        }
        return fieldText;
    }

    public Field[] getField() {
        return field;
    }
}
