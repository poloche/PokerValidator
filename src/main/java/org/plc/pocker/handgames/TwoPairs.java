package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class TwoPairs extends AbstractGame {
    private static final int TWO_PAIRS_GAME_WEIGHT = 3;

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGame(TWO_PAIRS_GAME_WEIGHT);
        winnerResult.addTwoPairWinner(hand);
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !isTwoPairs(hand);
    }

    private boolean isTwoPairs(Hand hand) {
        if (hand.getMapCards().size() == 3) {
            int count = 0;
            for (Map.Entry<String, List<Card>> entry : hand.getMapCards().entrySet()) {
                if (entry.getValue().size() == 2) {
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }
}
