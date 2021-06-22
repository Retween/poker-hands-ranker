package com.siberteam.edu.zernest.ranker;

import java.util.*;

public class PokerHand implements Comparable<PokerHand> {
    private final HandCombinations combination;
    private final String hand;
    int highValue;
//    int totalValue;

    public PokerHand(String hand) {
        this.hand = hand;
        List<Card> cardsList = getCardsHand(hand);

        List<Integer> ranks = getRanks(cardsList);
        boolean straight = checkStraight(ranks);

        List<Integer> suits = getSuits(cardsList);
        boolean flush = suits.stream().allMatch(suits.get(0)::equals);

        highValue = Collections.max(ranks);
//        totalValue = values.stream().mapToInt(v -> v).sum();

        combination = findCombination(flush, straight, ranks);
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

    private HandCombinations findCombination(boolean flush, boolean straight, List<Integer> values) {
        if (flush && straight) {
            return values.contains(Card.RANKS.indexOf('A')) ? HandCombinations.RoyalFlush
                    : HandCombinations.StraightFlush;
        } else if (flush) {
            return HandCombinations.Flush;
        } else if (straight) {
            return HandCombinations.Straight;
        }

        switch ((int) values.stream().distinct().count()) {
            case 5:
                return HandCombinations.HighCard;
            case 4:
                return HandCombinations.Pair;
            case 3:
                return getMaxDuplicatesCount(values) == 3 ? HandCombinations.Three
                        : HandCombinations.TwoPairs;
            case 2:
                return getMaxDuplicatesCount(values) == 3 ? HandCombinations.FullHouse
                        : HandCombinations.Four;
        }

        return HandCombinations.HighCard;
    }

    private boolean checkStraight(List<Integer> ranks) {
        boolean straigth = true;
        for (int i = 1; i < ranks.size(); i++) {
            if (!ranks.get(i).equals(ranks.get(i - 1) + 1)) {
                straigth = false;
                break;
            }
        }
        return straigth;
    }

    private int getMaxDuplicatesCount(List<Integer> values) {
        int last = values.get(0);
        int max = 0;
        int current = 1;

        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) == last) current++;
            else {
                max = Math.max(max, current);
                current = 1;
            }
            last = values.get(i);
        }
//        highValue = values.stream()
//                .filter(value -> value.equals(Collections.max(values.stream()
//                        .filter(v -> Collections.frequency(values, v) == maxDuplicatesCount)
//                        .collect(Collectors.toList())
//                )))
//                .mapToInt(v -> v).sum();
//        System.out.println(highValue + " " + maxDuplicatesCount + " | " + hand);
        return Math.max(max, current);
    }

    public HandCombinations getCombination() {
        return combination;
    }

    public String getHand() {
        return hand;
    }

    @Override
    public int compareTo(PokerHand o) {
        int combinationComparing = Integer.compare(combination.getScore(), o.combination.getScore());
//        if (combinationComparing == 0) {
//
//        }
        return combinationComparing == 0 ? Integer.compare(this.highValue, o.highValue) : combinationComparing;
//        return 0;
    }

//    private int getCombinationsValues(List<Integer> values) {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PokerHand hand1 = (PokerHand) o;
        return combination == hand1.combination && hand.equals(hand1.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(combination, hand);
    }

    @Override
    public String toString() {
        return "PokerHand" + "[" +
                "combination=" + combination +
                ", score=" + combination.getScore() +
                ", hand=" + hand +
                ']';
    }
}
