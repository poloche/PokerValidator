import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.plc.pocker.Hand;
import org.plc.pocker.PokerGame;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.handgames.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerGameTest {
    private static WinnerResult winnerResult = new WinnerResult();

    @BeforeAll
    public static void startWinner() {
        winnerResult = new WinnerResult();
    }

    @Test
    public void testWinnerIsRoyalFlush() {
        PokerGame game = new PokerGame();
        game.addHand("AD KD QD JD 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        RoyalFlush royalFlush = new RoyalFlush();
        assertTrue(royalFlush.checkGame(winners.get(0), winnerResult));
        assertEquals(winners.get(0).showCards(), "AD KD QD JD 10D");
    }

    @Test
    public void testWinnerIsPoker() {
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH AS 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        Poker poker = new Poker();
        assertTrue(poker.checkGame(winners.get(0), winnerResult));
        assertEquals(winners.get(0).showCards(), "AD AC AH AS 10D");
    }
//
    @Test
    public void testWinnerIsPokerWeight(){
        PokerGame game = new PokerGame();
        game.addHand("KD KC KH KS 9D");
        game.addHand("AD AC AH AS 10D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        Poker poker = new Poker();
        assertTrue(poker.checkGame(winners.get(0), winnerResult));
        assertEquals("AD AC AH AS 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsFull(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 10S 10D");
        game.addHand("KD QD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        FullHouse fullHouse = new FullHouse();
        assertTrue(fullHouse.checkGame(winners.get(0), winnerResult));
        assertEquals("AD AC AH 10S 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsThree(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 8S 10D");
        game.addHand("KD QD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        Three three = new Three();
        assertTrue(three.checkGame(winners.get(0), winnerResult));
        assertEquals("AD AC AH 8S 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsTwoPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 10S 10D");
        game.addHand("KD KD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        TwoPairs twoPairs = new TwoPairs();
        assertTrue(twoPairs.checkGame(winners.get(0), winnerResult));
        assertEquals("AD AC 8H 10S 10D", winners.get(0).showCards());
    }

    @Test
    public void testWinnerIsPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 1S 10D");
        game.addHand("KD 5D JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);

        Pairs pairs = new Pairs();
        assertTrue(pairs.checkGame(winners.get(0), winnerResult));
        assertEquals("AD AC 8H 1S 10D", winners.get(0).showCards());
    }
}
