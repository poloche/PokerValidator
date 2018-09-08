package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Poker extends AbstractGame {
    private static final BigInteger POKER_GAME_WEIGHT = new BigInteger("8");

    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        hand.setGameWeight(POKER_GAME_WEIGHT);
        winnerResult.addPokerWinner(hand);
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return !isPoker(hand);
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
