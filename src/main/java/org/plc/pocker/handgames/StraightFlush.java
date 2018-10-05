package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Player;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StraightFlush extends AbstractGame {
    private static final BigInteger STRAIGHT_FLUSH_GAME_WEIGHT = new BigInteger("241944");
    private BigInteger handWeight = new BigInteger("0");

    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(STRAIGHT_FLUSH_GAME_WEIGHT.multiply(handWeight));
        player.setCardsToChange(new ArrayList<Card>());
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return !hasAllSameSuit(player.getHand().getCards()) || !isInSequence(player.getHand().getCards());
    }

    private boolean isInSequence(List<Card> cards) {
        Collections.sort(cards, new NumberComparator());
        int startValue = 0;
        for (Card card : cards) {
            handWeight = handWeight.add(card.getNumber());
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
