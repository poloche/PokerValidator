package org.plc.pocker.handgames;

import org.plc.pocker.*;

import java.util.List;

public abstract class AbstractGame implements WhichHand {
    private WhichHand next;

    @Override
    public void setNext(WhichHand winner) {
        this.next=winner;
    }

    @Override
    public WhichHand getNext() {
        return next;
    }


    @Override
    public boolean checkGame(Player player) {
        if (isNext(player)) {
            return next.checkGame(player);
        } else {
            return takeResponsibility(player);
        }
    }

    protected abstract boolean takeResponsibility(Player player);

    public abstract boolean isNext(Player player);
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
