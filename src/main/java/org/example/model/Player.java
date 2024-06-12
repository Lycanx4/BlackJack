package org.example.model;

import org.example.ColorCode;
import org.example.Utility;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int totalMoney;
    private int bet;
    private List<Card> hand;
    private boolean isLoss;
    private boolean isWin;



    public Player(String name){
        this.name = name;
        this.bet = 0;
        this.hand = new ArrayList<>();
        this.isLoss = false;
        this.isWin = false;
        totalMoney = 100;
    }

    public String getName() {
        return name;
    }

    public int getTotalMoney() {
        return totalMoney;
    }
    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }
    public int getBet() {
        return bet;
    }

    public void placeBet(int bet) {
        if(bet>this.totalMoney){
            System.out.println(ColorCode.CYAN + "You don't have that much to bet, set your bet to all-in." + ColorCode.RESET);
            this.bet = this.totalMoney;
        }else{
            this.bet = bet;
        }
    }

    public void drawCard(Card card){
        this.hand.add(card);
        if(getTotalScore()>21){
            isLoss = true;
        }
    }

    public int getTotalScore(){
        int score = 0;
        boolean isOne = false;
        for(Card card: hand){
            if(card.getScore() == 1){
               isOne = true;
            }
            score += card.getScore();
        }
        if(isOne && score+11 <= 21){
            score += 10;
        }
        return score;
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
