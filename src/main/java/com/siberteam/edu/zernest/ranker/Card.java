package com.siberteam.edu.zernest.ranker;

public class Card {
    public final static String RANKS = "23456789TJQKA";
    public final static String SUITS = "SHDC";
    private final static int ADDITION = 10;

    private final int rank;
    private final int suit;

    public Card(String card) {
        rank = RANKS.indexOf(card.charAt(0));
        suit = SUITS.indexOf(card.charAt(1));
        if (rank == -1 || suit == -1 || card.length() != 2) {
            throw new IllegalArgumentException("Invalid card");
        }
    }

    public int getRank() {
        return rank + ADDITION;
    }

    public static int getRank(char rank) {
        return RANKS.indexOf(rank) + ADDITION;
    }

    public int getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "Card" + "[" +
                "rank=" + rank +
                ", suit=" + suit +
                ']';
    }
}
