package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Suit;
import org.plc.pocker.WinnerResult;

import java.util.List;

public class RoyalFlush implements WhichHand {
    private static final int ROYAL_FLUSH_WEIGHT = 60;
    private WhichHand next;

    @Override
    public void setNext(WhichHand winner) {
        next = winner;
    }

    @Override
    public WhichHand getNext() {
        return next;
    }

    @Override
    public boolean checkGame(Hand hand, WinnerResult winnerResult) {
        if (!hasAllSameSuit(hand.getCards()) || hand.getHandWeight() != ROYAL_FLUSH_WEIGHT) {
            next.checkGame(hand, winnerResult);
            return false;
        } else {
            winnerResult.addRoyalWinner(hand);
            return true;
        }
    }

    private boolean hasAllSameSuit(List<Card> cards) {
        Suit suit = null;
        for (Card card : cards) {
            if (suit == null) {
                suit = card.getSuit();
            } else {
                if (!suit.equals(card.getSuit())) {
                    return false;
                }
            }
        }
        return true;
    }
}
