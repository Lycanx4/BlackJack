package org.example.model;

import org.example.ColorCode;

import java.util.Scanner;

public class Game {
    private Deck deck = new Deck();
    private int numberOfPlayer;
    private Player[] players;
    private Scanner scanner = new Scanner(System.in);

    public Game(int numberOfPlayer){
        while (!selectNumberOfPlayer());
        players = new Player[numberOfPlayer];
    }

    public Player getPlayer(int idx){
        return players[idx];
    }

    public Player[] getAllPlayers(){
        return players;
    }
    public void run(){



        Player player = new Player("K");
        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());
        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());

        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());

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
}
