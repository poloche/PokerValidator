import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerGameTest {

    @Test
    public void testWinnerIsRoyalFlush(){
        PokerGame game = new PokerGame();
        game.addHand("AD KD QD JD 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isRoyalFlush());
    }

    @Test
    public void testWinnerIsPoker(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH AS 10D");
        game.addHand("KD QD JD 10D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isPoker());
    }

    @Test
    public void testWinnerIsPokerWeight(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH AS 10D");
        game.addHand("KD KC KH KS 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isPoker());
        assertEquals(winners.get(0).getHandWeight().intValue(),66);
    }

    @Test
    public void testWinnerIsFull(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 10S 10D");
        game.addHand("KD QD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isFullHouse());
    }

    @Test
    public void testWinnerIsThree(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC AH 8S 10D");
        game.addHand("KD QD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isThree());
    }
    @Test
    public void testWinnerIsTwoPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 10S 10D");
        game.addHand("KD KD JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isTwoPairs());
    }

    @Test
    public void testWinnerIsPairs(){
        PokerGame game = new PokerGame();
        game.addHand("AD AC 8H 1S 10D");
        game.addHand("KD 5D JD 1D 9D");
        List<Hand> winners = game.getWinner();
        assertNotNull(winners);
        assertTrue(winners.get(0).isPair());
    }
}
