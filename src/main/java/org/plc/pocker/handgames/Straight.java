package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class Straight extends AbstractGame {
    private static final BigInteger STRAIGHT_GAME_WEIGHT = new BigInteger("310");

    @Override
    protected boolean takeResponsibility(Hand hand) {
        hand.setGameWeight(STRAIGHT_GAME_WEIGHT.multiply(hand.getHandWeight()));
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !isInSequence(hand.getCards());
    }

    private boolean isInSequence(List<Card> cards) {
        Collections.sort(cards, new NumberComparator());
        int startValue = 0;
        for (Card card : cards) {
            if (startValue == 0) {
                startValue = card.getNumber().intValue();
            } else {
                if ((++startValue) != card.getNumber().intValue()) {
                    return false;
                }
            }
        }
        return true;
    }
}
