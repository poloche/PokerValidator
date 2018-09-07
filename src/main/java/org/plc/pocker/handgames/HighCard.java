package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.math.BigInteger;

public class HighCard extends AbstractGame {
    private static final BigInteger HIGH_CARD_GAME_WEIGHT = new BigInteger("1");

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        if (hand.getMapCards().size() == 5) {
            hand.setGameWeight(HIGH_CARD_GAME_WEIGHT);
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
