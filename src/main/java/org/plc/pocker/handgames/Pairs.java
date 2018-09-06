package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public class Pairs implements WhichHand {
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
        if (hand.getMapCards().size()==4) {
            winnerResult.addPairWinner(hand);
            return true;
        } else {
            next.checkGame(hand, winnerResult);
            return false;
        }
    }
}
