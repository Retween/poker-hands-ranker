package com.siberteam.edu.zernest.ranker;

public enum HandCombinations {
    HIGH_CARD(0), PAIR(1), TWO_PAIRS(2), THREE(3), STRAIGHT(4), FLUSH(5),
    FULL_HOUSE(6), FOUR(7), STRAIGHT_FLUSH(8), ROYAL_FLUSH(9);

    private final int score;

    HandCombinations(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "HandCombinations" + "[" +
                "Combination:" + this.name() +
                ", score=" + score +
                ']';
    }
}
