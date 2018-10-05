package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Player;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class TwoPairs extends AbstractGame {
    private static final BigInteger TWO_PAIRS_GAME_WEIGHT = new BigInteger("12");
    private BigInteger handWeight = new BigInteger("0");
    private List<Card> lowerCards;

    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(TWO_PAIRS_GAME_WEIGHT.multiply(handWeight));
        player.setCardsToChange(lowerCards);
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return !isTwoPairs(player.getHand());
    }

    private boolean isTwoPairs(Hand hand) {
        if (hand.getMapCards().size() == 3) {
            int count = 0;
            for (Map.Entry<String, List<Card>> entry : hand.getMapCards().entrySet()) {
                if (entry.getValue().size() == 2) {
                    calculateHandWeight(entry.getValue());
                    count++;
                } else {
                    lowerCards = entry.getValue();
                }
            }
            return count == 2;
        }
        return false;
    }

    private void calculateHandWeight(List<Card> cardList) {
        for (Card card : cardList) {
            handWeight = handWeight.add(card.getNumber());
        }
    }
}
