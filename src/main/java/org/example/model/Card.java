package org.example.model;

import org.example.ColorCode;
import org.example.Utility;

public class Card {
    private String rank;
    private String suit;

    public Card(String suit, String rank) {
        this.rank = rank;
        this.suit = suit;
    }

    public String getRank() {
        return rank;
    }

    public String getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return getCardACII();
    }
    public int getScore(){
        try {
            return Integer.parseInt(rank);
        }catch (NumberFormatException e){
            return 10;
        }
    }

    public String getCardACII(){
        String space = rank.equals("10") ? "   " : "    ";
        if(suit.equals(Utility.SPADE)){
            return ".------.\n" +
                    "|"+rank+suit+ space +"|\n" +
                    "|  /\\  |\n" +
                    "| (__) |\n" +
                    "|"+space+suit+rank+"|\n" +
                    "`------'";

        } else if(suit.equals(Utility.HEART)){
            return ColorCode.RED + ".------.\n" +
                    "|"+rank+suit+space +"|\n" +
                    "| (\\/) |\n" +
                    "|  \\/  |\n" +
                    "|"+space+suit+rank+"|\n" +
                    "`------'" + ColorCode.RESET;
        }else if(suit.equals(Utility.CLUB)){
            return ".------.\n" +
                    "|"+rank+suit+space +"|\n" +
                    "|  ()  |\n" +
                    "| ()() |\n" +
                    "|"+space+suit+rank+"|\n" +
                    "`------'";
        }else if(suit.equals(Utility.DIAMOND)){
            return ColorCode.RED + ".------.\n" +
                    "|"+rank+suit + space +"|\n" +
                    "|  /\\  |\n" +
                    "|  \\/  |\n" +
                    "|"+space+suit+rank+"|\n" +
                    "`------'" + ColorCode.RESET;
        }
        return "";
    }
}
