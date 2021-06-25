package com.siberteam.edu.zernest.ranker;

import java.util.Objects;

public class Card implements Comparable<Card> {
    private final Rank cardRank;
    private final Suit cardSuit;

    public Card(String card) {
        cardRank = Rank.getRank(card.charAt(0));
        cardSuit = Suit.getSuit(card.charAt(1));
        if (cardRank == null || cardSuit == null || card.length() != 2) {
            throw new IllegalArgumentException("Invalid card: " + card);
        }
    }

    public Card(Rank cardRank, Suit cardSuit) {
        this.cardRank = cardRank;
        this.cardSuit = cardSuit;
        if (cardRank == null || cardSuit == null) {
            throw new IllegalArgumentException("Invalid card: " + cardRank + " " + cardSuit);
        }
    }

    public Rank getCardRank() {
        return cardRank;
    }

    public Suit getCardSuit() {
        return cardSuit;
    }

    private int getComparisonNumber() {
        return cardRank.ordinal() + (cardSuit.ordinal() + 1) * 100;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(getComparisonNumber(), o.getComparisonNumber());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardRank == card.cardRank && cardSuit == card.cardSuit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardRank, cardSuit);
    }

    @Override
    public String toString() {
        return "Card" + "[" +
                "cardRank=" + cardRank +
                ", cardSuit=" + cardSuit +
                ']';
    }
}
