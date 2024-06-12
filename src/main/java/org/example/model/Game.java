package org.example.model;

import org.example.ColorCode;

import java.util.Arrays;
import java.util.Scanner;

public class Game {
    private Deck deck = new Deck();
    private int numberOfPlayer = 0;
    private Player[] players;
    private Scanner scanner = new Scanner(System.in);
    private boolean isRoundPlaying = true;
    private Dealer dealer = new Dealer();

    public Game(){
        while (!selectNumberOfPlayer());
        players = new Player[numberOfPlayer];
        for(int i=0; i<numberOfPlayer; i++){
            getPlayerInformation(i);
        }
    }

    public Player getPlayer(int idx){
        return players[idx];
    }

    public Player[] getAllPlayers(){
        return players;
    }
    public void playRound(){
        deck = new Deck();
        Arrays.stream(players).forEach(player -> getBet(player));
        for(int i=0; i<2; i++){
            dealer.drawCard(deck.giveCard());
            for(Player player: players){
                Card card = deck.giveCard();
                player.drawCard(card);
                System.out.println(player.getName() + " got:\n" + player.displayCards());
                System.out.println("Press enter to continue...");
                String a = scanner.nextLine();
            }
        }
        Arrays.stream(players).forEach(player -> playerTakeTurn(player));

        System.out.println("Dealer turn:");
        dealerTakeTurn();
        dealer.clearHand();
        askForNewRound();

    }

    private void askForNewRound(){
        System.out.println("Do you guy(s) want to start a new round? (Enter Y/N)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            playRound();
        }else if(input.equalsIgnoreCase("N")){
            System.out.println("Thank you for playing");
        }else{
            System.out.println(ColorCode.RED + "!!!Please enter valid value!!!" + ColorCode.RESET);
            askForNewRound();
        }
    }

    private void dealerTakeTurn(){
        if(dealer.getTotalScore()>17){
            Arrays.stream(players).forEach(player -> checkPlayer(dealer,player));
        }else
        {
            dealer.drawCard(deck.giveCard());
            dealerTakeTurn();
        }
    }

    private void checkPlayer(Dealer dealer, Player player){
        System.out.println(dealer.getName() + " Cards:\n"+ dealer.displayCards());
        System.out.println(dealer.getName() + " score:"+ dealer.getTotalScore() );
        if((dealer.getTotalScore()>player.getTotalScore() && dealer.getTotalScore()<=21) || (player.getTotalScore()>21 && dealer.getTotalScore()<=21)){
            dealer.dealerWin(player);
            System.out.println(player.getName() + " loss " + player.getBet() + ". Total balance: " + player.getTotalMoney());
        } else if ((dealer.getTotalScore()<player.getTotalScore()) || (player.getTotalScore()<=21 && dealer.getTotalScore()>21)) {
            dealer.dealerLoss(player);
            System.out.println(player.getName() + " win " + player.getBet() + ". Total balance: " + player.getTotalMoney());
        }else {
            System.out.println(player.getName() + " draw. Total balance: " + player.getTotalMoney());
        }
        player.clearHand();
        System.out.println("Press enter to continue...");
        String a = scanner.nextLine();
    }
    private boolean playerTakeTurn(Player player){
        System.out.println(player.getName() + " Cards:\n"+ player.displayCards());
        System.out.println(player.getName() + " score:"+ player.getTotalScore() );
        if(player.getTotalScore() >= 21){
            System.out.println("Press enter to continue...");
            String a = scanner.nextLine();
            return false;
        }
        return playerDrawCard(player);

    }
    private boolean playerDrawCard(Player player){
        if(player.getTotalScore()>=21){
            return false;
        }
        System.out.println("Do you want to draw a card? (Enter Y/N)");
        String input = scanner.nextLine();
        if(input.equalsIgnoreCase("Y")){
            player.drawCard(deck.giveCard());
            System.out.println(player.getName() + " Cards:\n"+ player.displayCards());
            System.out.println(player.getName() + " score:"+ player.getTotalScore() );
            System.out.println("Press enter to continue...");
            String a = scanner.nextLine();
            return playerDrawCard(player);
        }else if(input.equalsIgnoreCase("N")){
            return false;
        }else{
            System.out.println(ColorCode.RED + "!!!Please enter valid value!!!" + ColorCode.RESET);
            return playerDrawCard(player);
        }
    }
    private void getBet(Player player){
        System.out.println(player.getName() + " turn:");
        System.out.println("Please place a bet:");
        String input = scanner.nextLine();
        if(player.getTotalMoney()==0){
            System.out.println("Not enough money; you are out. However, I will allow you to play without any bet. :)");
            player.placeBet(0);
        }
        try {
            int bet = Integer.parseInt(input);
            if(bet<=0){
                System.out.println(ColorCode.CYAN + "Please enter valid value!!!" + ColorCode.RESET);
                getBet(player);
            }else {
                player.placeBet(bet);
            }

        }catch (NumberFormatException e){
            System.out.println(ColorCode.CYAN + "Please enter valid number!!!" + ColorCode.RESET);
            getBet(player);
        }
    }

    private boolean selectNumberOfPlayer(){
        System.out.println("Please enter the number of players (Max Player: 5)");
        String numString = scanner.nextLine();
        int num = 0;
        try{
            num = Integer.parseInt(numString);
        }catch (NumberFormatException e){
            System.out.println(ColorCode.RED + "Please enter valid number." + ColorCode.RESET);
            return false;
        }
        if(num>5){
            System.out.println(ColorCode.YELLOW + "The number of player should not exceed 5."+ ColorCode.RESET);
            return false;
        }
        this.numberOfPlayer = num;
        return true;
    }

    public void getPlayerInformation(int id){
        System.out.println("Please enter the player-" + (id+1) + " name:");
        String name = scanner.nextLine();
        Player player = new Player(name,id+1);
        players[id] = player;
    }
}
