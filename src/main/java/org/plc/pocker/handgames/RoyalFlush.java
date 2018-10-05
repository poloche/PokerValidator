package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Player;

import java.math.BigInteger;
import java.util.ArrayList;

public class RoyalFlush extends AbstractGame {
    private static final int ROYAL_FLUSH_CARDS_WEIGHT = 60;
    private static final BigInteger ROYAL_FLUSH_GAME_WEIGHT = new BigInteger("221785");


    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(ROYAL_FLUSH_GAME_WEIGHT.multiply(new BigInteger(String.valueOf(ROYAL_FLUSH_CARDS_WEIGHT))));
        player.setCardsToChange(new ArrayList<Card>());
        return true;
    }

    @Override
    public boolean isNext(Player player) {
        return !hasAllSameSuit(player.getHand().getCards()) || player.getHand().getHandWeight().intValue() != ROYAL_FLUSH_CARDS_WEIGHT;
    }
}
