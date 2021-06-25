import com.siberteam.edu.zernest.ranker.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHandRankerTest {

    @Test
    public void compareSameCards() {
        Assert.assertEquals(new Card("2H"), new Card(Rank.TWO, Suit.HEARTS));
        Assert.assertEquals(new Card("AC"), new Card(Rank.ACE, Suit.CLUBS));
        Assert.assertEquals(new Card("TD"), new Card(Rank.TEN, Suit.DIAMONDS));
        Assert.assertEquals(new Card("JS"), new Card(Rank.JACK, Suit.SPADES));
        Assert.assertEquals(new Card("KH"), new Card(Rank.KING, Suit.HEARTS));
    }

    @Test
    public void identifyRank() {
        Assert.assertEquals(Rank.TWO, Rank.getRank('2'));
        Assert.assertEquals(Rank.FIVE, Rank.getRank('5'));
        Assert.assertEquals(Rank.EIGHT, Rank.getRank('8'));
        Assert.assertEquals(Rank.JACK, Rank.getRank('J'));
        Assert.assertEquals(Rank.ACE, Rank.getRank('A'));
    }

    @Test
    public void identifySuit() {
        Assert.assertEquals(Suit.SPADES, Suit.getSuit('S'));
        Assert.assertEquals(Suit.DIAMONDS, Suit.getSuit('D'));
        Assert.assertEquals(Suit.HEARTS, Suit.getSuit('H'));
        Assert.assertEquals(Suit.CLUBS, Suit.getSuit('C'));
    }

    @Test
    public void compareSameHandsWithDifferentOrder() {
        Assert.assertEquals(new PokerHand("TS JS AS KS QS"), new PokerHand("QS JS TS AS KS"));
        Assert.assertEquals(new PokerHand("KC KH KD 2S KS"), new PokerHand("2S KH KC KD KS"));
        Assert.assertEquals(new PokerHand("TS JS AS KS QS"), new PokerHand("KS QS TS JS AS"));
        Assert.assertEquals(new PokerHand("2S KC 2H KH KD"), new PokerHand("2S 2H KC KH KD"));
        Assert.assertEquals(new PokerHand("3S 2H 5C JD TD"), new PokerHand("TD JD 5C 3S 2H"));
    }

    @Test
    public void compareCardListWithPokerHandList() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card("7C"));
        cardList.add(new Card(Rank.EIGHT, Suit.CLUBS));
        cardList.add(new Card("JS"));
        cardList.add(new Card(Rank.ACE, Suit.HEARTS));
        cardList.add(new Card("3H"));
        Collections.sort(cardList);

        Assert.assertEquals(cardList, new PokerHand("3H 7C 8C AH JS").getCardsList());
    }

    @Test
    public void findHighCardCombination() {
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("3S 2H 5C JD TD").getCombination());
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("5D 2C TH JC QS").getCombination());
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("KH 7C 5H 4D 2S").getCombination());
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("AD QH 9H 5C 3D").getCombination());
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("JC TS 7H 6S 2H").getCombination());
    }

    @Test
    public void findPairCombination() {
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("2H 3H 3S KD QS").getCombination());
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("AC AH JS 7C 4S").getCombination());
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("QD QH KH TC 2D").getCombination());
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("8S 8C AD JD 9D").getCombination());
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("2H 2S TH 8C 4D").getCombination());
    }

    @Test
    public void findTwoPairCombination() {
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("2S 3H 3C KD KH").getCombination());
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("AS AD QC QD 5H").getCombination());
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("JC JH 8S 8H AC").getCombination());
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("9H 9D 5D 5C KD").getCombination());
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("6C 6H 2D 2C TC").getCombination());
    }

    @Test
    public void findThreeCombination() {
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("2S 3H KC KD KH").getCombination());
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("AS AC AD JH 4C").getCombination());
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("QH QD QS AD 8D").getCombination());
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("9C 9S 9H KH 7C").getCombination());
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("2D 2S 2C JH TS").getCombination());
    }

    @Test
    public void findStraightCombination() {
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("TS KH JD QC AD").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("AH KD QS JH TC").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("QD JC TH 9H 8S").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("7H 6C 5H 4D 3C").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("4C 5C 3H 2D 6H").getCombination());
    }

    @Test
    public void findFlushCombination() {
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("2C 3C AC 4C 5C").getCombination());
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("AD QD TD 7D 4D").getCombination());
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("JS 9S 8S 4S 3S").getCombination());
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("TH 7H 4H 3H 2H").getCombination());
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("8C 7C 5C 4C 3C").getCombination());
    }

    @Test
    public void findFullHouseCombination() {
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("2S 2H KC KH KD").getCombination());
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("AS AH AC QH QD").getCombination());
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("JD JC JS 2S 2H").getCombination());
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("TC TS TH AD AS").getCombination());
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("4H 4S 4D KS KH").getCombination());
    }

    @Test
    public void findFourCombination() {
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("2S KH KC KD KS").getCombination());
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("AS AH AC AD 5C").getCombination());
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("JS JC JD JH 9H").getCombination());
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("7D 7S 7H 7C KD").getCombination());
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("4S 4D 4H 4C TD").getCombination());
    }

    @Test
    public void findStraightFlushCombination() {
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("TS JS 9S KS QS").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("KS QS JS TS 9S").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("9H 8H 7H 6H 5H").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("QC JC TC 9C 8C").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("5D 6D 4D 3D 2D").getCombination());
    }

    @Test
    public void findRoyalFlushCombination() {
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("TS JS AS KS QS").getCombination());
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("AS KS QS JS TS").getCombination());
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("AH KH QH JH TH").getCombination());
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("AC KC QC JC TC").getCombination());
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("AD KD QD JD TD").getCombination());
    }

    @Test
    public void compareHighCardCombinations() {
        Assert.assertEquals(0, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AC QD 9D 5D 3C")));
        Assert.assertEquals(1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("2C QD 9D 5D 3C")));
        Assert.assertEquals(1, new PokerHand("KH 7C 6H 4D 2S").compareTo(new PokerHand("JC TS 7H 6S 2H")));
        Assert.assertEquals(1, new PokerHand("JC TS 7H 6S 2H").compareTo(new PokerHand("9C 8D 7D 6D 4D")));
    }

    @Test
    public void comparePairCombinations() {
        Assert.assertEquals(0, new PokerHand("2H 3H 3S KD QS").compareTo(new PokerHand("2S 3C 3D KH QD")));
        Assert.assertEquals(1, new PokerHand("QD QH KH TC 2D").compareTo(new PokerHand("JD JH KH TC 2D")));
        Assert.assertEquals(1, new PokerHand("8S 8C AD JD 9D").compareTo(new PokerHand("8S 8C KD JD 9D")));
        Assert.assertEquals(1, new PokerHand("AC AH JS 7C 4S").compareTo(new PokerHand("AD AC JH 7C 3S")));
    }

    @Test
    public void compareTwoPairCombinations() {
        Assert.assertEquals(0, new PokerHand("2S 3H 3C KD KH").compareTo(new PokerHand("2H KC KH 3D 3C")));
        Assert.assertEquals(1, new PokerHand("AS AD QC QD 5H").compareTo(new PokerHand("3S 3D 2C 2D 5H")));
        Assert.assertEquals(1, new PokerHand("JC JH 8S 8H AC").compareTo(new PokerHand("JD JC 4C 4D AC")));
        Assert.assertEquals(1, new PokerHand("9H 9D 5D 5C KD").compareTo(new PokerHand("9S 9C 5D 5H JD")));
    }

    @Test
    public void compareThreeCombinations() {
        Assert.assertEquals(0, new PokerHand("AS AC AD JH 4C").compareTo(new PokerHand("AC AS AH JC 4D")));
        Assert.assertEquals(1, new PokerHand("QH QD QS AD 8D").compareTo(new PokerHand("3H 3D 3S 4D KD")));
        Assert.assertEquals(1, new PokerHand("9C 9S 9H KH 7C").compareTo(new PokerHand("9D 9S 9H JD TC")));
        Assert.assertEquals(1, new PokerHand("2D 2S 2C JH TS").compareTo(new PokerHand("2D 2S 2C JH 9C")));
    }

    @Test
    public void compareStraightCombinations() {
        Assert.assertEquals(0, new PokerHand("AH KD QS JH TC").compareTo(new PokerHand("AD KD QD JS TC")));
        Assert.assertEquals(1, new PokerHand("QD JC TH 9H 8S").compareTo(new PokerHand("2D 4C 3H 6H 5S")));
        Assert.assertEquals(1, new PokerHand("7H 6C 5H 4D 3C").compareTo(new PokerHand("2D 6H 5S 4D 3S")));
        Assert.assertEquals(1, new PokerHand("AH KD QS JH TC").compareTo(new PokerHand("4C 5C 3H 2D 6H")));
    }

    @Test
    public void compareFlushCombinations() {
        Assert.assertEquals(0, new PokerHand("AD QD TD 7D 4D").compareTo(new PokerHand("AC QC TC 7C 4C")));
        Assert.assertEquals(0, new PokerHand("JS 9S 8S 4S 3S").compareTo(new PokerHand("JH 9H 8H 4H 3H")));
        Assert.assertEquals(1, new PokerHand("TH 7H 4H 3H 2H").compareTo(new PokerHand("5H 7H 4H 3H 2H")));
        Assert.assertEquals(1, new PokerHand("AD QD TD 7D 4D").compareTo(new PokerHand("8C 7C 5C 4C 3C")));
    }

    @Test
    public void compareFullHouseCombinations() {
        Assert.assertEquals(0, new PokerHand("2S 2H KC KH KD").compareTo(new PokerHand("2D 2C KS KH KD")));
        Assert.assertEquals(1, new PokerHand("AS AH AC QH QD").compareTo(new PokerHand("KS KD KC QS QD")));
        Assert.assertEquals(1, new PokerHand("JD JC JH AC AD").compareTo(new PokerHand("JD JC JS 2S 2H")));
        Assert.assertEquals(1, new PokerHand("TC TS TH AD AS").compareTo(new PokerHand("5C 5S 5H AD AS")));
    }

    @Test
    public void compareFourCombinations() {
        Assert.assertEquals(0, new PokerHand("2S KH KC KD KS").compareTo(new PokerHand("2D KH KC KD KS")));
        Assert.assertEquals(1, new PokerHand("AS AH AC AD 5C").compareTo(new PokerHand("KS KH KC KD AC")));
        Assert.assertEquals(1, new PokerHand("JS JC JD JH 9H").compareTo(new PokerHand("TS TC TD TH 9H")));
        Assert.assertEquals(1, new PokerHand("7D 7S 7H 7C KD").compareTo(new PokerHand("2D 2S 2H 2C AD")));
    }

    @Test
    public void compareStraightFlushCombinations() {
        Assert.assertEquals(0, new PokerHand("TS JS 9S KS QS").compareTo(new PokerHand("TH JH 9H KH QH")));
        Assert.assertEquals(1, new PokerHand("KS QS JS TS 9S").compareTo(new PokerHand("8C QC JC TC 9C")));
        Assert.assertEquals(1, new PokerHand("9H 8H 7H 6H 5H").compareTo(new PokerHand("2H 3H 4H 6H 5H")));
        Assert.assertEquals(1, new PokerHand("QC JC TC 9C 8C").compareTo(new PokerHand("7H 6H TH 9H 8H")));
    }

    @Test
    public void compareRoyalFlushCombinations() {
        Assert.assertEquals(0, new PokerHand("AS KS QS JS TS").compareTo(new PokerHand("AD KD QD JD TD")));
        Assert.assertEquals(0, new PokerHand("AH KH QH JH TH").compareTo(new PokerHand("AC KC QC JC TC")));
        Assert.assertEquals(0, new PokerHand("AC KC QC JC TC").compareTo(new PokerHand("AS KS QS JS TS")));
        Assert.assertEquals(0, new PokerHand("AD KD QD JD TD").compareTo(new PokerHand("AH KH QH JH TH")));
    }

    @Test
    public void compareHighCardAndPair() {
        Assert.assertEquals(-1, new PokerHand("2H 5C JD TD 3S").compareTo(new PokerHand("2H 3H 3S KD QS")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AC AH JS 7C 4S")));
    }

    @Test
    public void compareHighCardAndTwoPair() {
        Assert.assertEquals(-1, new PokerHand("5D 2C TH JC QS").compareTo(new PokerHand("2S 3H 3C KD KH")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AS AD QC QD 5H")));
    }

    @Test
    public void compareHighCardAndThree() {
        Assert.assertEquals(-1, new PokerHand("2C TH JC QS 5D").compareTo(new PokerHand("2S 3H KC KD KH")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AS AC AD JH 4C")));
    }

    @Test
    public void compareHighCardAndStraight() {
        Assert.assertEquals(-1, new PokerHand("5D 2C TH JC QS").compareTo(new PokerHand("TS KH JD QC AD")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AH KD QS JH TC")));
    }

    @Test
    public void compareHighCardAndFlush() {
        Assert.assertEquals(-1, new PokerHand("2C TH JC QS 5D").compareTo(new PokerHand("2C 3C AC 4C 5C")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AD QD TD 7D 4D")));
    }

    @Test
    public void compareHighCardAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("5D 2C TH JC QS").compareTo(new PokerHand("2S 2H KC KH KD")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AS AH AC QH QD")));
    }

    @Test
    public void compareHighCardAndFour() {
        Assert.assertEquals(-1, new PokerHand("2C TH JC QS 5D").compareTo(new PokerHand("2S KH KC KD KS")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AS AH AC AD 5C")));
    }

    @Test
    public void compareHighCardAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("5D 2C TH JC QS").compareTo(new PokerHand("TS JS 9S KS QS")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("KS QS JS TS 9S")));
    }

    @Test
    public void compareHighCardAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("2C TH JC QS 5D").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("AD QH 9H 5C 3D").compareTo(new PokerHand("AS KS QS JS TS")));
    }

    @Test
    public void comparePairAndTwoPair() {
        Assert.assertEquals(-1, new PokerHand("3H 3S 2H KD QS").compareTo(new PokerHand("2S 3H 3C KD KH")));
        Assert.assertEquals(-1, new PokerHand("8C AD 8S JD 9D").compareTo(new PokerHand("9H 9D 5D 5C KD")));
    }

    @Test
    public void comparePairAndThree() {
        Assert.assertEquals(-1, new PokerHand("2H 3H 3S KD QS").compareTo(new PokerHand("2S 3H KC KD KH")));
        Assert.assertEquals(-1, new PokerHand("8S 8C AD JD 9D").compareTo(new PokerHand("QH QD QS AD 8D")));
    }

    @Test
    public void comparePairAndStraight() {
        Assert.assertEquals(-1, new PokerHand("3H 3S 2H KD QS").compareTo(new PokerHand("AH KD QS JH TC")));
        Assert.assertEquals(-1, new PokerHand("8C AD 8S JD 9D").compareTo(new PokerHand("4C 5C 3H 2D 6H")));
    }

    @Test
    public void comparePairAndFlush() {
        Assert.assertEquals(-1, new PokerHand("2H 3H 3S KD QS").compareTo(new PokerHand("2C 3C AC 4C 5C")));
        Assert.assertEquals(-1, new PokerHand("8S 8C AD JD 9D").compareTo(new PokerHand("TH 7H 4H 3H 2H")));
    }

    @Test
    public void comparePairAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("3H 3S 2H KD QS").compareTo(new PokerHand("2S 2H KC KH KD")));
        Assert.assertEquals(-1, new PokerHand("8C AD 8S JD 9D").compareTo(new PokerHand("JD JC JS 2S 2H")));
    }

    @Test
    public void comparePairAndFour() {
        Assert.assertEquals(-1, new PokerHand("2H 3H 3S KD QS").compareTo(new PokerHand("2S KH KC KD KS")));
        Assert.assertEquals(-1, new PokerHand("8S 8C AD JD 9D").compareTo(new PokerHand("AS AH AC AD 5C")));
    }

    @Test
    public void comparePairAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("3H 3S 2H KD QS").compareTo(new PokerHand("TS JS 9S KS QS")));
        Assert.assertEquals(-1, new PokerHand("8C AD 8S JD 9D").compareTo(new PokerHand("QC JC TC 9C 8C")));
    }

    @Test
    public void comparePairAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("2H 3H 3S KD QS").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("8S 8C AD JD 9D").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareTwoPairAndThree() {
        Assert.assertEquals(-1, new PokerHand("AD AS QC QD 5H").compareTo(new PokerHand("AS AC AD JH 4C")));
        Assert.assertEquals(-1, new PokerHand("6H 6C 2D 2C TC").compareTo(new PokerHand("9C 9S 9H KH 7C")));
    }

    @Test
    public void compareTwoPairAndStraight() {
        Assert.assertEquals(-1, new PokerHand("AS AD QC QD 5H").compareTo(new PokerHand("QD JC TH 9H 8S")));
        Assert.assertEquals(-1, new PokerHand("6C 6H 2D 2C TC").compareTo(new PokerHand("4C 5C 3H 2D 6H")));
    }

    @Test
    public void compareTwoPairAndFlush() {
        Assert.assertEquals(-1, new PokerHand("AD AS QC QD 5H").compareTo(new PokerHand("2C 3C AC 4C 5C")));
        Assert.assertEquals(-1, new PokerHand("6H 6C 2D 2C TC").compareTo(new PokerHand("TH 7H 4H 3H 2H")));
    }

    @Test
    public void compareTwoPairAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("AS AD QC QD 5H").compareTo(new PokerHand("JD JC JS 2S 2H")));
        Assert.assertEquals(-1, new PokerHand("6C 6H 2D 2C TC").compareTo(new PokerHand("4H 4S 4D KS KH")));
    }

    @Test
    public void compareTwoPairAndFour() {
        Assert.assertEquals(-1, new PokerHand("AD AS QC QD 5H").compareTo(new PokerHand("2S KH KC KD KS")));
        Assert.assertEquals(-1, new PokerHand("6H 6C 2D 2C TC").compareTo(new PokerHand("4S 4D 4H 4C TD")));
    }

    @Test
    public void compareTwoPairAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("AS AD QC QD 5H").compareTo(new PokerHand("9H 8H 7H 6H 5H")));
        Assert.assertEquals(-1, new PokerHand("6C 6H 2D 2C TC").compareTo(new PokerHand("5D 6D 4D 3D 2D")));
    }

    @Test
    public void compareTwoPairAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("AD AS QC QD 5H").compareTo(new PokerHand("AC KC QC JC TC")));
        Assert.assertEquals(-1, new PokerHand("6H 6C 2D 2C TC").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareThreeAndStraight() {
        Assert.assertEquals(-1, new PokerHand("AS AC AD JH 4C").compareTo(new PokerHand("TS KH JD QC AD")));
        Assert.assertEquals(-1, new PokerHand("2D 2S 2C JH TS").compareTo(new PokerHand("QD JC TH 9H 8S")));
    }

    @Test
    public void compareThreeAndFlush() {
        Assert.assertEquals(-1, new PokerHand("AC AS AD JH 4C").compareTo(new PokerHand("AD QD TD 7D 4D")));
        Assert.assertEquals(-1, new PokerHand("2S 2D 2C JH TS").compareTo(new PokerHand("TH 7H 4H 3H 2H")));
    }

    @Test
    public void compareThreeAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("AS AC AD JH 4C").compareTo(new PokerHand("2S 2H KC KH KD")));
        Assert.assertEquals(-1, new PokerHand("2D 2S 2C JH TS").compareTo(new PokerHand("TC TS TH AD AS")));
    }

    @Test
    public void compareThreeAndFour() {
        Assert.assertEquals(-1, new PokerHand("AC AS AD JH 4C").compareTo(new PokerHand("JS JC JD JH 9H")));
        Assert.assertEquals(-1, new PokerHand("2S 2D 2C JH TS").compareTo(new PokerHand("7D 7S 7H 7C KD")));
    }

    @Test
    public void compareThreeAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("AS AC AD JH 4C").compareTo(new PokerHand("TS JS 9S KS QS")));
        Assert.assertEquals(-1, new PokerHand("2D 2S 2C JH TS").compareTo(new PokerHand("QC JC TC 9C 8C")));
    }

    @Test
    public void compareThreeAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("AC AS AD JH 4C").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("2S 2D 2C JH TS").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareStraightAndFlush() {
        Assert.assertEquals(-1, new PokerHand("TS JD KH QC AD").compareTo(new PokerHand("AD QD TD 7D 4D")));
        Assert.assertEquals(-1, new PokerHand("7H 5H 6C 4D 3C").compareTo(new PokerHand("8C 7C 5C 4C 3C")));
    }

    @Test
    public void compareStraightAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("TS KH JD QC AD").compareTo(new PokerHand("2S 2H KC KH KD")));
        Assert.assertEquals(-1, new PokerHand("7H 6C 5H 4D 3C").compareTo(new PokerHand("TC TS TH AD AS")));
    }

    @Test
    public void compareStraightAndFour() {
        Assert.assertEquals(-1, new PokerHand("TS JD KH QC AD").compareTo(new PokerHand("2S KH KC KD KS")));
        Assert.assertEquals(-1, new PokerHand("7H 5H 6C 4D 3C").compareTo(new PokerHand("7D 7S 7H 7C KD")));
    }

    @Test
    public void compareStraightAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("TS KH JD QC AD").compareTo(new PokerHand("TS JS 9S KS QS")));
        Assert.assertEquals(-1, new PokerHand("7H 6C 5H 4D 3C").compareTo(new PokerHand("5D 6D 4D 3D 2D")));
    }

    @Test
    public void compareStraightAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("KH TS JD QC AD").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("6C 7H 5H 4D 3C").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareFlushAndFullHouse() {
        Assert.assertEquals(-1, new PokerHand("2C 3C AC 4C 5C").compareTo(new PokerHand("2S 2H KC KH KD")));
        Assert.assertEquals(-1, new PokerHand("TH 7H 4H 3H 2H").compareTo(new PokerHand("TC TS TH AD AS")));
    }

    @Test
    public void compareFlushAndFour() {
        Assert.assertEquals(-1, new PokerHand("3C 2C AC 4C 5C").compareTo(new PokerHand("2S KH KC KD KS")));
        Assert.assertEquals(-1, new PokerHand("7H TH 4H 3H 2H").compareTo(new PokerHand("4S 4D 4H 4C TD")));
    }

    @Test
    public void compareFlushAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("2C 3C AC 4C 5C").compareTo(new PokerHand("KS QS JS TS 9S")));
        Assert.assertEquals(-1, new PokerHand("TH 7H 4H 3H 2H").compareTo(new PokerHand("QC JC TC 9C 8C")));
    }

    @Test
    public void compareFlushAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("2C AC 3C 4C 5C").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("TH 4H 7H 3H 2H").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareFullHouseAndFour() {
        Assert.assertEquals(-1, new PokerHand("AS AH AC QH QD").compareTo(new PokerHand("AS AH AC AD 5C")));
        Assert.assertEquals(-1, new PokerHand("4H 4S 4D KS KH").compareTo(new PokerHand("4S 4D 4H 4C TD")));
    }

    @Test
    public void compareFullHouseAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("AS AH QH AC QD").compareTo(new PokerHand("KS QS JS TS 9S")));
        Assert.assertEquals(-1, new PokerHand("4H 4S KS 4D KH").compareTo(new PokerHand("QC JC TC 9C 8C")));
    }

    @Test
    public void compareFullHouseAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("QH AS AH AC QD").compareTo(new PokerHand("AH KH QH JH TH")));
        Assert.assertEquals(-1, new PokerHand("KS 4H 4S 4D KH").compareTo(new PokerHand("TS JS AS KS QS")));
    }

    @Test
    public void compareFourAndStraightFlush() {
        Assert.assertEquals(-1, new PokerHand("2S KH KC KD KS").compareTo(new PokerHand("TS JS 9S KS QS")));
        Assert.assertEquals(-1, new PokerHand("AS AH AC AD 5C").compareTo(new PokerHand("5D 6D 4D 3D 2D")));
    }

    @Test
    public void compareFourAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("2S KC KH KD KS").compareTo(new PokerHand("TS JS AS KS QS")));
        Assert.assertEquals(-1, new PokerHand("AS AC AH AD 5C").compareTo(new PokerHand("AC KC QC JC TC")));
    }

    @Test
    public void compareStraightFlushAndRoyalFlush() {
        Assert.assertEquals(-1, new PokerHand("QS KS JS TS 9S").compareTo(new PokerHand("AH KH QH JH TH")));
        Assert.assertEquals(-1, new PokerHand("JC QC TC 9C 8C").compareTo(new PokerHand("AC KC QC JC TC")));
    }
}
