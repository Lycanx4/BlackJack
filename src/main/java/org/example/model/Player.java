package org.example.model;

import org.example.ColorCode;
import org.example.Utility;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int bet;
    private boolean isBanker;
    private List<Card> hand;


    public Player(String name){
        this.name = name;
        this.isBanker = false;
        this.bet = 0;
        this.hand = new ArrayList<>();
    }

    public void drawCard(Card card){
        this.hand.add(card);
    }

    public String displayCards(){
        String result = "";
        String[] strs = new String[6];
        for(Card card : hand){
            String[] texts = card.getCardACII().split("\n");
            boolean isRed = card.getSuit().equals(Utility.DIAMOND) ||
                            card.getSuit().equals(Utility.HEART);
            for(int i = 0; i<6; i++){
                texts[i] = texts[i].replace(ColorCode.RED,"");
                texts[i] = texts[i].replace(ColorCode.RESET,"");
                if(strs[i] == null){
                    strs[i] = isRed? (ColorCode.RED + texts[i] + " " +ColorCode.RESET):(texts[i] + " ");
                }else{
                    strs[i] += isRed? (ColorCode.RED + texts[i] + " " +ColorCode.RESET):(texts[i] + " ");
                }
            }
        }
        result = String.join("\n",strs);
        return result;
    }

}
