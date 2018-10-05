package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Player;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class Three extends AbstractGame {
    private static final BigInteger THREE_GAME_WEIGHT = new BigInteger("110");

    private BigInteger handWeight = new BigInteger("0");
    private List<Card> lowerCards;

    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(THREE_GAME_WEIGHT.multiply(handWeight));
        player.setCardsToChange(lowerCards);
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return !isThree(player.getHand());
    }

    private boolean isThree(Hand hand) {
        if (hand.getMapCards().size() == 3) {
            for (Map.Entry<String, List<Card>> cards : hand.getMapCards().entrySet()) {
                if (cards.getValue().size() == 3) {
                    calculateHandWeight(cards.getValue());
                    return true;
                } else {
                    lowerCards = cards.getValue();
                }
            }
        }
        return false;
    }

    private void calculateHandWeight(List<Card> cardList) {
        for (Card card : cardList) {
            handWeight = handWeight.add(card.getNumber());
        }
    }
}
