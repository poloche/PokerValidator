package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class FullHouse implements WhichHand {
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
        if (isFull(hand)) {
            winnerResult.addFullWinner(hand);
            return true;
        } else {
            next.checkGame(hand, winnerResult);
            return false;
        }
    }

    private boolean isFull(Hand hand) {
        boolean hasPair = false;
        boolean hasThree = false;
        if (hand.getMapCards().size() == 2) {
            for (Map.Entry<String, List<Card>> entry : hand.getMapCards().entrySet()) {
                if (entry.getValue().size() == 2) {
                    hasPair = true;
                }
                if (entry.getValue().size() == 3) {
                    hasThree = true;
                }
            }
        }
        return hasPair && hasThree;
    }
}
