package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public class Pairs extends AbstractGame {
    private static final int PAIR_GAME_WEIGHT = 2;

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

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGame(PAIR_GAME_WEIGHT);
        winnerResult.addPairWinner(hand);

        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return hand.getMapCards().size()!=4;
    }
}
