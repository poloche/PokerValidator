package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Suit;
import org.plc.pocker.WinnerResult;

import java.util.List;

public abstract class AbstractGame implements WhichHand {
    protected WhichHand next;

    @Override
    public void setNext(WhichHand winner) {
        this.next=winner;
    }

    @Override
    public WhichHand getNext() {
        return next;
    }


    @Override
    public boolean checkGame(Hand hand, WinnerResult winnerResult) {
        if (isNext(hand)) {
            return next.checkGame(hand, winnerResult);
        } else {
            return takeResponsibility(hand, winnerResult);
        }
    }

    protected abstract boolean takeResponsibility(Hand hand, WinnerResult winnerResult);

    public abstract boolean isNext(Hand hand);
    protected boolean hasAllSameSuit(List<Card> cards) {
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
