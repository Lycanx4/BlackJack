package org.example.model;

public class Dealer extends Player{
    public Dealer() {
        super("Dealer");
    }
    public void dealerWin(Player player){
        player.setTotalMoney(player.getTotalMoney() - player.getBet());
    }
    public void dealerLoss(Player player){
        player.setTotalMoney(player.getTotalMoney() + player.getBet());
    }
}
