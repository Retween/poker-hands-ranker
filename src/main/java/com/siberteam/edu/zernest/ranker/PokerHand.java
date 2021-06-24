package com.siberteam.edu.zernest.ranker;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {
    private final HandCombinations combination;
    private final long sameCombinationScore;
    private final List<Card> cardsList;
    public String hand;///////////////

    public PokerHand(String hand) {
        this.hand = hand;
        cardsList = getCardsFromString(hand);

        List<Rank> ranksList = getRanks(cardsList);
        List<Suit> suitsList = getSuits(cardsList);
        int maxDuplicatesCount = findMaxDuplicatesCount(ranksList);

        combination = findCombination(isFlush(suitsList), isStraight(ranksList), maxDuplicatesCount, ranksList);

        sameCombinationScore = findSameCombinationScore(getRanksMap(ranksList), maxDuplicatesCount);
    }

    private List<Card> getCardsFromString(String stringHand) {
        List<Card> cardsHand = new ArrayList<>();
        Arrays.stream(stringHand.split(" ")).forEach(s -> cardsHand.add(new Card(s)));
        Collections.sort(cardsHand);
        return cardsHand;
    }

    private List<Suit> getSuits(List<Card> cardsList) {
        List<Suit> suits = new ArrayList<>();
        cardsList.forEach(card -> suits.add(card.getCardSuit()));
        return suits;
    }

    private List<Rank> getRanks(List<Card> cardsList) {
        List<Rank> ranks = new ArrayList<>();
        cardsList.forEach(card -> ranks.add(card.getCardRank()));
        Collections.sort(ranks);
        return ranks;
    }

    private Map<Integer, Integer> getRanksMap(List<Rank> ranks) {
        Map<Integer, Integer> ranksMap = new LinkedHashMap<>();
        ranks.forEach(rank -> ranksMap.merge(rank.getValue(), 1, Integer::sum));
        return ranksMap;
    }

    private boolean isStraight(List<Rank> ranks) {
        boolean straight = true;

        for (int i = 1; i < ranks.size(); i++) {
            if (ranks.get(i).getValue() != ranks.get(i - 1).getValue() + 1) {
                straight = false;
                break;
            }
        }

        return straight;
    }

    private boolean isFlush(List<Suit> suits) {
        return suits.stream().allMatch(suits.get(0)::equals);
    }

    private int findMaxDuplicatesCount(List<Rank> values) {
        int last = values.get(0).getValue();
        int max = 0;
        int current = 1;

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i).getValue() == last) {
                current++;
            } else {
                max = Math.max(max, current);
                current = 1;
            }
            last = values.get(i).getValue();
        }

        return Math.max(max, current);
    }

    private HandCombinations findCombination(boolean flush, boolean straight, int maxDuplicatesCount,
                                             List<Rank> values) {
        if (flush && straight) {
            return values.contains(Rank.ACE) ? HandCombinations.ROYAL_FLUSH
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

    @Override
    public int compareTo(PokerHand o) {
        int combinationComparing = Integer.compare(combination.getScore(), o.combination.getScore());

        return combinationComparing == 0 ? Long.compare(sameCombinationScore, o.sameCombinationScore)
                : combinationComparing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand hand = (PokerHand) o;
        return sameCombinationScore == hand.sameCombinationScore
                && combination == hand.combination
                && cardsList.equals(hand.cardsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combination, sameCombinationScore, cardsList);
    }

    @Override
    public String toString() {
        return "PokerHand" + "[" +
                "combination=" + combination +
                ", sameCombinationScore=" + sameCombinationScore +
                ", cardsList=" + cardsList +
                ']';
    }
}
