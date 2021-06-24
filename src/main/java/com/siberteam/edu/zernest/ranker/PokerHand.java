package com.siberteam.edu.zernest.ranker;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {
    private final HandCombinations combination;
    private final String hand;
    private final long sameCombinationScore;

    public PokerHand(String hand) {
        this.hand = hand;
        List<Card> cardsList = getCardsHand(hand);
        List<Integer> ranks = getRanks(cardsList);
        List<Integer> suits = getSuits(cardsList);
        int maxDuplicatesCount = findMaxDuplicatesCount(ranks);

        combination = findCombination(isFlush(suits), isStraight(ranks), maxDuplicatesCount, ranks);

        sameCombinationScore = findSameCombinationScore(getRanksMap(ranks), maxDuplicatesCount);
    }

    private List<Card> getCardsHand(String stringHand) {
        List<Card> cardsHand = new ArrayList<>();
        Arrays.stream(stringHand.split(" ")).forEach(s -> cardsHand.add(new Card(s)));
        return cardsHand;
    }

    private List<Integer> getSuits(List<Card> cardsList) {
        List<Integer> suits = new ArrayList<>();
        cardsList.forEach(card -> suits.add(card.getSuit()));
        return suits;
    }

    private List<Integer> getRanks(List<Card> cardsList) {
        List<Integer> ranks = new ArrayList<>();
        cardsList.forEach(card -> ranks.add(card.getRank()));
        Collections.sort(ranks);
        return ranks;
    }

    private Map<Integer, Integer> getRanksMap(List<Integer> ranks) {
        Map<Integer, Integer> ranksMap = new LinkedHashMap<>();
        ranks.forEach(rank -> ranksMap.merge(rank, 1, Integer::sum));
        return ranksMap;
    }

    private boolean isStraight(List<Integer> ranks) {
        boolean straight = true;

        for (int i = 1; i < ranks.size(); i++) {
            if (!ranks.get(i).equals(ranks.get(i - 1) + 1)) {
                straight = false;
                break;
            }
        }

        return straight;
    }

    private boolean isFlush(List<Integer> suits) {
        return suits.stream().allMatch(suits.get(0)::equals);
    }

    private int findMaxDuplicatesCount(List<Integer> values) {
        int last = values.get(0);
        int max = 0;
        int current = 1;

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) == last) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 1;
            }
            last = values.get(i);
        }

        return Math.max(max, current);
    }

    private HandCombinations findCombination(boolean flush, boolean straight, int maxDuplicatesCount,
                                             List<Integer> values) {
        if (flush && straight) {
            return values.contains(Card.getRank('A')) ? HandCombinations.ROYAL_FLUSH
                    : HandCombinations.STRAIGHT_FLUSH;
        } else if (flush) {
            return HandCombinations.FLUSH;
        } else if (straight) {
            return HandCombinations.STRAIGHT;
        }

        switch ((int) values.stream().distinct().count()) {
            case 5:
                return HandCombinations.HIGH_CARD;
            case 4:
                return HandCombinations.PAIR;
            case 3:
                return maxDuplicatesCount == 3 ? HandCombinations.THREE
                        : HandCombinations.TWO_PAIRS;
            case 2:
                return maxDuplicatesCount == 3 ? HandCombinations.FULL_HOUSE
                        : HandCombinations.FOUR;
        }

        return HandCombinations.HIGH_CARD;
    }

    private long findSameCombinationScore(Map<Integer, Integer> ranksMap, int maxDuplicatesCount) {
        StringBuilder sameCombinationScore = new StringBuilder();
        int max = 0;

        while (true) {
            if (ranksMap.containsValue(maxDuplicatesCount)) {
                for (Map.Entry<Integer, Integer> rank : ranksMap.entrySet()) {
                    if (rank.getValue().equals(maxDuplicatesCount)) {
                        max = Math.max(max, rank.getKey());
                    }
                }
                sameCombinationScore.append(max);
                ranksMap.remove(max);
                max = 0;
            } else {
                maxDuplicatesCount--;
                if (maxDuplicatesCount <= 0) {
                    break;
                }
            }
        }

        return Long.parseLong(sameCombinationScore.toString());
    }

    public HandCombinations getCombination() {
        return combination;
    }

    public String getHand() {
        return hand;
    }

    public long getSameCombinationScore() {
        return sameCombinationScore;
    }

    @Override
    public int compareTo(PokerHand o) {
        int combinationComparing = Integer.compare(combination.getScore(), o.combination.getScore());

        return combinationComparing == 0 ? Long.compare(getSameCombinationScore(), o.getSameCombinationScore())
                : combinationComparing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand hand = (PokerHand) o;
        return sameCombinationScore == hand.sameCombinationScore && combination == hand.combination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(combination, sameCombinationScore);
    }

    @Override
    public String toString() {
        return "PokerHand" + "[" +
                "combination=" + combination +
                ", hand='" + hand + '\'' +
                ", sameCombinationScore=" + sameCombinationScore +
                ']';
    }
}
