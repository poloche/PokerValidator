package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.util.List;
import java.util.Map;

public class FullHouse extends AbstractGame {
    private static final int POKER_GAME_WEIGHT = 6;

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGame(POKER_GAME_WEIGHT);
        winnerResult.addFullWinner(hand);
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !isFull(hand);
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
