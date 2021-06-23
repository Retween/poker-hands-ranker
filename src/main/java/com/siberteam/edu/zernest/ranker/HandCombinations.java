package com.siberteam.edu.zernest.ranker;

public enum HandCombinations {
    HighCard(0), Pair(1), TwoPairs(2), Three(3), Straight(4), Flush(5),
    FullHouse(6), Four(7), StraightFlush(8), RoyalFlush(9);

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
