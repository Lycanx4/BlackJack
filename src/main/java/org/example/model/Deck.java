package org.example.model;

import org.example.Utility;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private List<Card> cards = new ArrayList<>();

    private Random random = new Random();

    public Deck(){
        for(String suit: Utility.SUITS){
            for(String rank: Utility.RANKS){
                Card card = new Card(suit,rank);
                cards.add(card);
            }
        }
        shuffleDeck();
    }

    public List<Card> getCards() {
        return cards;
    }

    public void shuffleDeck(){
        List<Card> shuffleCards = new ArrayList<>();
        while (cards.size()>0){
            int rand = random.nextInt(cards.size());
            shuffleCards.add(cards.get(rand));
            cards.remove(rand);
        }
        cards = shuffleCards;
    }

    public boolean cutDeck(int cuttingPoint){
        if(cuttingPoint>cards.size()){
            return false;
        }
        List<Card> firstPart = new ArrayList<>(cards.subList(0,cuttingPoint));
        List<Card> secondPart = new ArrayList<>(cards.subList(cuttingPoint,cards.size()));
        return true;
    }

    public Card giveCard(){
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
}
