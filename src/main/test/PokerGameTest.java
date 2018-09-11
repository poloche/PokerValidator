import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.plc.pocker.Hand;
import org.plc.pocker.PokerGame;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.handgames.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PokerGameTest {
    private static WinnerResult winnerResult = new WinnerResult();

    @BeforeAll
    public static void startWinner() {
        winnerResult = new WinnerResult();
    }

    @Test
    public void testWinnerByWrightIsRoyalFlush() {
        PokerGame game = new PokerGame();
        game.addHand("AD KD QD JD 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);
        RoyalFlush royalFlush = new RoyalFlush();
        assertTrue(royalFlush.checkGame(winners.get(0)));
        assertEquals(winners.get(0).showCards(), "AD KD QD JD 10D");
    }

    @Test
    public void testWinnerByWeightIsStraightFlush() {
        PokerGame game = new PokerGame();
        game.addHand("AH KD QD JD 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);
        StraightFlush royalFlush = new StraightFlush();
        assertTrue(royalFlush.checkGame(winners.get(0)));
        assertEquals("9D 10D JD QD KD", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsPoker() {
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH AS 10D");
        game.addHand("KD QD JH 10D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        Poker poker = new Poker();
        assertTrue(poker.checkGame(winners.get(0)));
        assertEquals(winners.get(0).showCards(), "AD AC AH AS 10D");
    }

    @Test
    public void testWinnerIsPokerWeight(){
        PokerGame game = new PokerGame();
        game.addHand("KD KC KH KS 9D");
        game.addHand("AD AC AH AS 10D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        Poker poker = new Poker();
        assertTrue(poker.checkGame(winners.get(0)));
        assertEquals("AD AC AH AS 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsFull(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 10S 10D");
        game.addHand("KD QD JH 1D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        FullHouse fullHouse = new FullHouse();
        assertTrue(fullHouse.checkGame(winners.get(0)));
        assertEquals("AD AC AH 10S 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsThree(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 8S 10D");
        game.addHand("KD QD JH 1D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        Three three = new Three();
        assertTrue(three.checkGame(winners.get(0)));
        assertEquals("8S 10D AD AC AH", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsTwoPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 10S 10D");
        game.addHand("KD KD JH 1D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        TwoPairs twoPairs = new TwoPairs();
        assertTrue(twoPairs.checkGame(winners.get(0)));
        assertEquals("8H 10S 10D AD AC", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 1S 10D");
        game.addHand("KD 5D JH 1D 9D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        Pairs pairs = new Pairs();
        assertTrue(pairs.checkGame(winners.get(0)));
        assertEquals("1S 8H 10D AD AC", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsPairsHigh(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 2H 4S 3D");
        game.addHand("KD KC 2H 4S 3D");
        List<Hand> winners = game.getWinnerByWeight();
        assertNotNull(winners);

        Pairs pairs = new Pairs();
        assertTrue(pairs.checkGame(winners.get(0)));
        assertEquals("2H 3D 4S AD AC", winners.get(0).showCards());
    }
}
