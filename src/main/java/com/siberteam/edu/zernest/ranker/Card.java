package com.siberteam.edu.zernest.ranker;

public class Card {
    public final static String RANKS = "23456789TJQKA";
    public final static String SUITS = "SHDC";
    private final int rank;
    private final int suit;

    public Card(String card) {
        rank = RANKS.indexOf(card.charAt(0));
        suit = SUITS.indexOf(card.charAt(1));
        if (rank == -1 || suit == -1 || card.length() != 2) {
            throw new IllegalArgumentException();
        }
    }

    public int getRank() {
        return rank;
    }

    public int getSuit() {
        return suit;
    }

}
