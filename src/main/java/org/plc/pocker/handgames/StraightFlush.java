package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

public class StraightFlush extends AbstractGame {
    private static final BigInteger STRAIGHT_FLUSH_GAME_WEIGHT = new BigInteger("9");

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGameWeight(STRAIGHT_FLUSH_GAME_WEIGHT);
        winnerResult.addRoyalWinner(hand);
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !hasAllSameSuit(hand.getCards()) || !isInSequence(hand.getCards());
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
