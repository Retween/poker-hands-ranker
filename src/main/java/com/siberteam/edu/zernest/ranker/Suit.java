package com.siberteam.edu.zernest.ranker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Suit {
    SPADES('S'), HEARTS('H'), DIAMONDS('D'), CLUBS('C');

    private static final Map<Character, Suit> suitsCharactersMap = new HashMap<>();

    private final char suitChar;

    static {
        Arrays.stream(values()).forEach(suit -> suitsCharactersMap.put(suit.suitChar, suit));
    }

    Suit(char suitChar) {
        this.suitChar = suitChar;
    }

    public static Suit getSuit(char suitChar) {
        return suitsCharactersMap.get(suitChar);
    }

    @Override
    public String toString() {
        return "Suit" + "[" +
                "Suit=" + this.name() +
                ", suitChar=" + suitChar +
                ']';
    }
}
