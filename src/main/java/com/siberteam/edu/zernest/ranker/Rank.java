package com.siberteam.edu.zernest.ranker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum Rank {
    TWO('2', 10),
    THREE('3', 11),
    FOUR('4', 12),
    FIVE('5', 13),
    SIX('6', 14),
    SEVEN('7', 15),
    EIGHT('8', 16),
    NINE('9', 17),
    TEN('T', 18),
    JACK('J', 19),
    QUEEN('Q', 20),
    KING('K', 21),
    ACE('A', 22);

    private static final Map<Character, Rank> ranksCharacterMap = new HashMap<>();

    private final char rankChar;
    private final int value;

    static {
        Arrays.stream(values()).forEach(rank -> ranksCharacterMap.put(rank.rankChar, rank));
    }

    Rank(char rankChar, int value) {
        this.rankChar = rankChar;
        this.value = value;
    }

    public static Rank getRank(char rankChar) {
        return ranksCharacterMap.get(rankChar);
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Rank" + "[" +
                "Rank=" + this.name() +
                ", rankChar=" + rankChar +
                ", value=" + value +
                ']';
    }
}
