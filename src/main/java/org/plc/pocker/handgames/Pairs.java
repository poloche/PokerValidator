package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Player;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Pairs extends AbstractGame {
    private static final BigInteger PAIR_GAME_WEIGHT = new BigInteger("5");

    @Override
    protected boolean takeResponsibility(Player player) {
        BigInteger handWeight = new BigInteger("0");
        List<Card> lowCards = new ArrayList<>();
        for (List<Card> cardsEntry : player.getHand().getMapCards().values()) {
            if (cardsEntry.size() == 2) {
                for (Card card : cardsEntry) {
                    handWeight = handWeight.add(card.getNumber());
                }
            } else {
                lowCards.addAll(cardsEntry);
            }
        }
        player.getHand().setGameWeight(PAIR_GAME_WEIGHT.multiply(handWeight));
        player.setCardsToChange(lowCards);
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return player.getHand().getMapCards().size() != 4;
    }
}
