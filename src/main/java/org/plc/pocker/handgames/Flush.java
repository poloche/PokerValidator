package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.math.BigInteger;

public class Flush extends AbstractGame{
    private static final BigInteger FLUSH_GAME_WEIGHT = new BigInteger("847");
    @Override
    protected boolean takeResponsibility(Hand hand) {
        hand.setGameWeight(FLUSH_GAME_WEIGHT.multiply(hand.getHandWeight()));
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !hasAllSameSuit(hand.getCards());
    }
}
