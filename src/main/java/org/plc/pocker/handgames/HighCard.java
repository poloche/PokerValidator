package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public class HighCard extends AbstractGame {
    private static final int HIGH_CARD_GAME_WEIGHT = 1;

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        if (hand.getMapCards().size() == 5) {
            hand.setGame(HIGH_CARD_GAME_WEIGHT);
            winnerResult.addHighCard(hand);
            return true;
        }
        return false;
    }

    @Override
    public boolean isNext(Hand hand) {
        return false;
    }
}
