package models;

import fields.*;

import java.awt.*;

public class Board {

    Field[] field = new Field[40];

    public Board() {
        field[0] = new Start("Start", Color.RED);
        field[1] = new Street("Rødovrevej", 60, 2, Color.CYAN);
        field[2] = new Chance("Chance", Color.WHITE);
        field[3] = new Street("Hvidovrevej", 60, 4, Color.CYAN);
        field[4] = new Tax("Skat", 200, Color.LIGHT_GRAY);
        field[5] = new Shipping("Øresund", 200, Color.WHITE);
        field[6] = new Street("Roskildevej", 100, 6, Color.PINK);
        field[7] = new Chance("Chance", Color.WHITE);
        field[8] = new Street("Valby Langgade", 100, 6, Color.PINK);
        field[9] = new Street("Allégade", 120, 8, Color.PINK);
        field[10] = new Jail("Fængsel", Color.WHITE);
        field[11] = new Street("Frederiksberg Allé", 140, 10, Color.GREEN);
        field[12] = new Brewery("Tuborg", 150, Color.WHITE);
        field[13] = new Street("Bülowsvej", 140, 10, Color.GREEN);
        field[14] = new Street("Gammel Kongevej", 160, 12, Color.GREEN);
        field[15] = new Shipping("D.F.D.S.", 200, Color.WHITE);
        field[16] = new Street("Bernstorffsvej", 180, 14, Color.ORANGE);
        field[17] = new Chance("Chance", Color.WHITE);
        field[18] = new Street("Hellerupvej", 180, 14, Color.ORANGE);
        field[19] = new Street("Strandvejen", 200, 16, Color.ORANGE);
        field[20] = new Parking("Helle", Color.WHITE);
        field[21] = new Street("Trianglen", 220, 18, Color.RED);
        field[22] = new Chance("Chance", Color.WHITE);
        field[23] = new Street("Østerbrogade", 220, 18, Color.RED);
        field[24] = new Street("Grønningen", 240, 20, Color.RED);
        field[25] = new Shipping("Ø.S.", 200, Color.WHITE);
        field[26] = new Street("Bredgade", 260, 22, Color.BLUE);
        field[27] = new Street("Kgs. Nytorv ", 260, 22, Color.BLUE);
        field[28] = new Brewery("Carlsberg", 150, Color.WHITE);
        field[29] = new Street("Østergade", 280, 24, Color.BLUE);
        field[30] = new GoToJail("Gå i fængsel", Color.WHITE);
        field[31] = new Street("Amagertorv", 300, 26, Color.YELLOW);
        field[32] = new Street("Vimmelskaftet", 300, 26, Color.YELLOW);
        field[33] = new Chance("Chance", Color.WHITE);
        field[34] = new Street("Nygade", 320, 30,Color.YELLOW);
        field[35] = new Shipping("Bornholm", 200, Color.WHITE);
        field[36] = new Chance("Chance", Color.WHITE);
        field[37] = new Street("Frederiksberggade", 350, 35, Color.MAGENTA);
        field[38] = new Tax("Skat", 100, Color.LIGHT_GRAY);
        field[39] = new Street("Rådhuspladsen", 400, 50, Color.MAGENTA);
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
}
