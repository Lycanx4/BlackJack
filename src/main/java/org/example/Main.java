package org.example;

import org.example.model.Card;
import org.example.model.Deck;
import org.example.model.Player;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        run();
    }
    public static void run(){
        Deck deck = new Deck();
        System.out.println();
        Player player = new Player("K");
        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());
        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());

        player.drawCard(deck.drawCard());
        System.out.println(player.displayCards());

    }
}