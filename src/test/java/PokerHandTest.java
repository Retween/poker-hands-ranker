import com.siberteam.edu.zernest.ranker.HandCombinations;
import com.siberteam.edu.zernest.ranker.IOHandsListOperator;
import com.siberteam.edu.zernest.ranker.PokerHand;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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
        expectedHandsList.add(new PokerHand("3S 2H 5C JD TD"));
        expectedHandsList.add(new PokerHand("2H 3H 3S KD QS"));
        expectedHandsList.add(new PokerHand("2S 3H 3C KD KH"));
        expectedHandsList.add(new PokerHand("2S 3H KC KD KH"));
        expectedHandsList.add(new PokerHand("TS KH JD QC AD"));
        expectedHandsList.add(new PokerHand("2C 3C AC 4C 5C"));
        expectedHandsList.add(new PokerHand("2S 2H KC KH KD"));
        expectedHandsList.add(new PokerHand("2S KH KC KD KS"));
        expectedHandsList.add(new PokerHand("TS JS 9S KS QS"));
        expectedHandsList.add(new PokerHand("TS JS AS KS QS"));
    }

    @Test
    public void sortHandsTest() {
        Assert.assertEquals(expectedHandsList, actualHansList);
    }

    @Test
    public void findCombinationTest() {
        Assert.assertEquals(HandCombinations.HighCard, new PokerHand("3S 2H 5C JD TD").getCombination());
        Assert.assertEquals(HandCombinations.Pair, new PokerHand("2H 3H 3S KD QS").getCombination());
        Assert.assertEquals(HandCombinations.TwoPairs, new PokerHand("2S 3H 3C KD KH").getCombination());
        Assert.assertEquals(HandCombinations.Three, new PokerHand("2S 3H KC KD KH").getCombination());
        Assert.assertEquals(HandCombinations.Straight, new PokerHand("TS KH JD QC AD").getCombination());
        Assert.assertEquals(HandCombinations.Flush, new PokerHand("2C 3C AC 4C 5C").getCombination());
        Assert.assertEquals(HandCombinations.FullHouse, new PokerHand("2S 2H KC KH KD").getCombination());
        Assert.assertEquals(HandCombinations.Four, new PokerHand("2S KH KC KD KS").getCombination());
        Assert.assertEquals(HandCombinations.StraightFlush, new PokerHand("TS JS 9S KS QS").getCombination());
        Assert.assertEquals(HandCombinations.RoyalFlush, new PokerHand("TS JS AS KS QS").getCombination());
    }
}
