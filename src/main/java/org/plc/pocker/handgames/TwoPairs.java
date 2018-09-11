package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class TwoPairs extends AbstractGame {
    private static final BigInteger TWO_PAIRS_GAME_WEIGHT = new BigInteger("12");
    private BigInteger handWeight = new BigInteger("0");

    @Override
    protected boolean takeResponsibility(Hand hand) {
        hand.setGameWeight(TWO_PAIRS_GAME_WEIGHT.multiply(handWeight));
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
                    calculateHandWeight(entry.getValue());
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }

    private void calculateHandWeight(List<Card> cardList) {
        for (Card card : cardList) {
            handWeight = handWeight.add(card.getNumber());
        }
    }
}
