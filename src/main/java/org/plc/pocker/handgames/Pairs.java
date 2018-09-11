package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Pairs extends AbstractGame {
    private static final BigInteger PAIR_GAME_WEIGHT = new BigInteger("5");

    @Override
    protected boolean takeResponsibility(Hand hand) {
        BigInteger handWeight = new BigInteger("0");
        for (Map.Entry<String, List<Card>> cardsEntry : hand.getMapCards().entrySet()) {
            if(cardsEntry.getValue().size()==2){
                for (Card card : cardsEntry.getValue()) {
                    handWeight = handWeight.add(card.getNumber());
                }
            }
        }
        hand.setGameWeight(PAIR_GAME_WEIGHT.multiply(handWeight));
        return true;
    }

    @Override
    public boolean isNext(Hand hand) {
        return hand.getMapCards().size() != 4;
    }
}
