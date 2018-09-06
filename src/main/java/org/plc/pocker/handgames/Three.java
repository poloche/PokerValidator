package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class Three implements WhichHand {
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
        if (isThree(hand)) {
            winnerResult.addThreeWinner(hand);
            return true;
        } else {
            next.checkGame(hand, winnerResult);
            return false;
        }
    }

    private boolean isThree(Hand hand) {
        if (hand.getMapCards().size() == 3) {
            for (Map.Entry<String, List<Card>> cards : hand.getMapCards().entrySet()) {
                if (cards.getValue().size() == 3) {
                    return true;
                }
            }
        }
        return false;
    }
}
