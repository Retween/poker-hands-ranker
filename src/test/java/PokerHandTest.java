import com.siberteam.edu.zernest.ranker.HandCombinations;
import com.siberteam.edu.zernest.ranker.Test.IOHandsListOperator;
import com.siberteam.edu.zernest.ranker.PokerHand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerHandTest {
    List<PokerHand> expectedHandsList;
    List<PokerHand> actualHansList;

    @Before
    public void setUp() throws IOException {
        File file = new File("src/test/data/testHands.txt");

        actualHansList = IOHandsListOperator.readHandsList(new FileInputStream(file));
        Collections.sort(actualHansList);

        expectedHandsList = new ArrayList<>();
        expectedHandsList.add(new PokerHand("3S 2H 5C JD TD"));//HighCard
        expectedHandsList.add(new PokerHand("4S 2H 5C JD TD"));//HighCard
        expectedHandsList.add(new PokerHand("2H 3H 3S KD QS"));//Pair
        expectedHandsList.add(new PokerHand("5H 3S 3H KD QS"));//Pair
        expectedHandsList.add(new PokerHand("2S 3H 3C KD KH"));//TwoPairs
        expectedHandsList.add(new PokerHand("9S 3H 3C KD KH"));//TwoPairs
        expectedHandsList.add(new PokerHand("2S 3H KC KD KH"));//Three
        expectedHandsList.add(new PokerHand("9S 3H KC KD KH"));//Three
        expectedHandsList.add(new PokerHand("2S 3H 4D 5C 6D"));//Straight
        expectedHandsList.add(new PokerHand("TS KH JD QC AD"));//Straight
        expectedHandsList.add(new PokerHand("2C 3C AC 4C 5C"));//Flush
        expectedHandsList.add(new PokerHand("2C QC AC KC 5C"));//Flush
        expectedHandsList.add(new PokerHand("2S 2H KC KH KD"));//FullHouse
        expectedHandsList.add(new PokerHand("AS AH KC KH KD"));//FullHouse
        expectedHandsList.add(new PokerHand("2S KH KC KD KS"));//Four
        expectedHandsList.add(new PokerHand("4S KH KC KD KS"));//Four
        expectedHandsList.add(new PokerHand("TS JS 9S KS QS"));//StraightFlush
        expectedHandsList.add(new PokerHand("TS JS KS AS QS"));//RoyalFlush
    }

    @Test
    public void sortHandsTest() {
        Assert.assertEquals(expectedHandsList, actualHansList);
    }

    @Test
    public void findCombinationTest() {
        Assert.assertEquals(HandCombinations.HIGH_CARD, new PokerHand("3S 2H 5C JD TD").getCombination());
        Assert.assertEquals(HandCombinations.PAIR, new PokerHand("2H 3H 3S KD QS").getCombination());
        Assert.assertEquals(HandCombinations.TWO_PAIRS, new PokerHand("2S 3H 3C KD KH").getCombination());
        Assert.assertEquals(HandCombinations.THREE, new PokerHand("2S 3H KC KD KH").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT, new PokerHand("TS KH JD QC AD").getCombination());
        Assert.assertEquals(HandCombinations.FLUSH, new PokerHand("2C 3C AC 4C 5C").getCombination());
        Assert.assertEquals(HandCombinations.FULL_HOUSE, new PokerHand("2S 2H KC KH KD").getCombination());
        Assert.assertEquals(HandCombinations.FOUR, new PokerHand("2S KH KC KD KS").getCombination());
        Assert.assertEquals(HandCombinations.STRAIGHT_FLUSH, new PokerHand("TS JS 9S KS QS").getCombination());
        Assert.assertEquals(HandCombinations.ROYAL_FLUSH, new PokerHand("TS JS AS KS QS").getCombination());
    }
}
