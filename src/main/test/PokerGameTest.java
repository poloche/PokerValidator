import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PokerGameTest {

    @Test
    public void testWinnerIsRoyalFlush(){
        PokerGame game = new PokerGame();
        game.addHand("AD KD QD JD 10D");
        game.addHand("KD QD JD 10D 9D");
        Hand winner = game.getWinner();
        assertTrue(winner.isRoyalFlush());
    }
}
