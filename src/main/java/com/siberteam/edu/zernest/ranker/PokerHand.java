package com.siberteam.edu.zernest.ranker;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private static final Map<String, Integer> cardValues = new HashMap<>();
    private final HandCombinations combination;
    private final String hand;
    //    int highValue;
    int totalValue;

    static {
        cardValues.put("T", 10);
        cardValues.put("J", 11);
        cardValues.put("Q", 12);
        cardValues.put("K", 13);
        cardValues.put("A", 14);
    }

    public PokerHand(String hand) {
        this.hand = hand;
        List<String> cards = new ArrayList<>(Arrays.asList(hand.split(" ")));

        List<Integer> values = getValues(cards);
//        highValue = Collections.max(values);
        totalValue = values.stream().mapToInt(v -> v).sum();
//        System.out.println(totalValue);
        boolean straight = checkStraight(values);

        List<String> suits = getSuits(cards);
        boolean flush = suits.stream().allMatch(suits.get(0)::equals);

        combination = findCombination(flush, straight, values);
    }

    private List<String> getSuits(List<String> cards) {
        List<String> list = new ArrayList<>(cards);
        list.replaceAll(s -> s.substring(1));
        return list;
    }

    private List<Integer> getValues(List<String> cards) {
        List<String> values = new ArrayList<>(cards);
        values.replaceAll(s -> s.substring(0, 1));

        List<Integer> intValues = new ArrayList<>();

        for (String value : values) {
            Integer intValue = cardValues.get(value);
            if (intValue != null) {
                intValues.add((int) Math.pow(2, intValue));
            } else {
                intValues.add((int) Math.pow(2, Integer.parseInt(value)));
            }
        }

        Collections.sort(intValues);
        return intValues;
    }

    private HandCombinations findCombination(boolean flush, boolean straight, List<Integer> values) {
        if (flush && straight) {
            return values.contains(cardValues.get("A")) ? HandCombinations.RoyalFlush
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

    private boolean checkStraight(List<Integer> values) {
        boolean straigth = true;
        for (int i = 1; i < values.size(); i++) {
            if (!values.get(i).equals(values.get(i - 1) + 1)) {
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
//
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
        if (combinationComparing == 0) {

        }
        //        return combinationComparing == 0 ? Integer.compare(this.highValue, o.highValue) : combinationComparing;
        return 0;
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
