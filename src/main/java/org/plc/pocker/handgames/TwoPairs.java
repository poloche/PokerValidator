package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class TwoPairs implements WhichHand {
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
        if (isTwoPairs(hand)) {
            winnerResult.addTwoPairWinner(hand);
            return true;
        } else {
            next.checkGame(hand, winnerResult);
            return false;
        }
    }

    private boolean isTwoPairs(Hand hand) {
        if (hand.getMapCards().size() == 3) {
            int count = 0;
            for (Map.Entry<String, List<Card>> entry : hand.getMapCards().entrySet()) {
                if (entry.getValue().size() == 2) {
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }
}