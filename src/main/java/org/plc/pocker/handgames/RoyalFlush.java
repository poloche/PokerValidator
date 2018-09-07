package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public class RoyalFlush extends AbstractGame {
    private static final int ROYAL_FLUSH_CARDS_WEIGHT = 60;
    private static final int ROYAL_FLUSH_GAME_WEIGHT = 10;


    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGame(ROYAL_FLUSH_GAME_WEIGHT);
        winnerResult.addRoyalWinner(hand);
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !hasAllSameSuit(hand.getCards()) || hand.getHandWeight() != ROYAL_FLUSH_CARDS_WEIGHT;
    }
}
