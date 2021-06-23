package com.siberteam.edu.zernest.ranker;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IOHandsListOperator {

    public static List<PokerHand> readHandsList(InputStream inputStream) throws IOException {
        List<PokerHand> hands = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                hands.add(new PokerHand(inputLine));
            }
        }

        return hands;
    }

    public static void writeHandsList(OutputStream outputStream, List<PokerHand> hands) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            for (PokerHand hand : hands) {
                bw.write(hand.getHand() + ": " + hand.getCombination().name() + "\n");
            }
        }
    }
}
