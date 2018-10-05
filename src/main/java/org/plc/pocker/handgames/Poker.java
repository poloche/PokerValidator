package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Player;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Poker extends AbstractGame {
    private static final BigInteger POKER_GAME_WEIGHT = new BigInteger("32403");
    private BigInteger handWeight = new BigInteger("1");
    private ArrayList<Card> lowerCards;

    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(POKER_GAME_WEIGHT.multiply(handWeight));
        player.setCardsToChange(lowerCards);
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return !isPoker(player.getHand());
    }

    private boolean isPoker(Hand hand) {
        Map<String, List<Card>> cards = hand.getMapCards();
        if (cards.size() == 2) {
            for (Map.Entry<String, List<Card>> cardsEntry : cards.entrySet()) {
                if (cardsEntry.getValue().size() == 1 || cardsEntry.getValue().size() == 4) {
                    if (cardsEntry.getValue().size() == 4) {
                        calculateHandWeight(cardsEntry.getValue());
                    } else {
                        lowerCards = new ArrayList<>(cardsEntry.getValue());
                    }
                    return true;
                }
            }
        }
        return false;
    }

    private void calculateHandWeight(List<Card> listEntry) {
        handWeight = new BigInteger("0");
        for (Card card : listEntry) {
            handWeight = handWeight.add(card.getNumber());
        }
    }
}
