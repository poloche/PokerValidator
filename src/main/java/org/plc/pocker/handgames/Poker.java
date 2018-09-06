package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class Poker implements WhichHand {
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
        if (isPoker(hand)) {
            winnerResult.addPokerWinner(hand);
            return true;
        } else {
            next.checkGame(hand, winnerResult);
            return false;
        }
    }

    private boolean isPoker(Hand hand) {
        Map<String, List<Card>> cards = hand.getMapCards();
        if (cards.size() == 2) {
            for (Map.Entry<String, List<Card>> card : cards.entrySet()) {
                if (card.getValue().size() == 1 || card.getValue().size() == 4) {
                    return true;
                }
            }
        }
        return false;
    }
}
