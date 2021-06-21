package com.siberteam.edu.zernest.ranker;

import java.util.*;
import java.util.stream.Collectors;

public class PokerHand implements Comparable<PokerHand> {
    private final List<Integer> values;
    private final List<String> suits;
    private final boolean flush;
    private final boolean straight;
    private final int highCard;
    private final int uniqueValues;

    public PokerHand(String hand) {
        List<String> cards = new ArrayList<>(Arrays.asList(hand.split(" ")));

        values = getValues(cards);
        highCard = Collections.max(values);
        straight = values.get(4) - values.get(0) == 4;
        uniqueValues = (int) values.stream().distinct().count();

        System.out.println(values.stream().distinct().collect(Collectors.toList()));
        System.out.println(uniqueValues);
        System.out.println(values.size());

        suits = getSuits(cards);
        flush = suits.stream().allMatch(suits.get(0)::equals);
    }

    public List<String> getSuits(List<String> cards) {
        List<String> list = new ArrayList<>(cards);
        list.replaceAll(s -> s.substring(1));
        return list;
    }

    public List<Integer> getValues(List<String> cards) {
        List<String> values = new ArrayList<>(cards);
        values.replaceAll(s -> s.substring(0, 1));

        List<Integer> intValues = new ArrayList<>();

        for (String value : values) {
            switch (value) {
                case "T":
                    intValues.add(10);
                    break;
                case "J":
                    intValues.add(11);
                    break;
                case "Q":
                    intValues.add(12);
                    break;
                case "K":
                    intValues.add(13);
                    break;
                case "A":
                    intValues.add(14);
                    break;
                default:
                    intValues.add(Integer.parseInt(value));
            }
        }

        Collections.sort(intValues);
        return intValues;
    }

    private int getScore() {
        if (flush && straight) {
            return values.contains(14) ? 9 : 8;
        }
        if (straight) {
            return 4;
        }
        if (flush) {
            return 5;
        }
        if (uniqueValues == 4) {
            return 1;
        }
        if (uniqueValues == 3) {
            return 2;
        }
        if (uniqueValues == 2) {

        }
        return 0;
    }

    public static void main(String[] args) {
        PokerHand hand = new PokerHand("2S 1H KC KD KD");
//        PokerHand hand = new PokerHand("2S 1H 1C KD KD");

//        PokerHand hand = new PokerHand("2S 2H KC KD KD");
//        PokerHand hand = new PokerHand("2S KH KC KD KD");

        System.out.println(hand.flush);
        System.out.println(hand.straight);
    }

    @Override
    public int compareTo(PokerHand o) {
        return 0;
    }
}
