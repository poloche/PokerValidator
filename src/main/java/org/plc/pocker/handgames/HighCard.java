package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Player;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighCard extends AbstractGame {
    private static final BigInteger HIGH_CARD_GAME_WEIGHT = new BigInteger("1");

    @Override
    protected boolean takeResponsibility(Player player) {
        if (player.getHand().getMapCards().size() == 5) {
            List<Card> cards = player.getHand().getCards();
            Comparator<Card> cardComparator = Collections.reverseOrder(new NumberComparator());
            Collections.sort(cards, cardComparator);

            player.getHand().setGameWeight(HIGH_CARD_GAME_WEIGHT.multiply(cards.get(0).getNumber()));
            player.setCardsToChange(cards.subList(1,cards.size()));

            return true;
        }
        return false;
    }

    @Override
    public boolean isNext(Player player) {
        return false;
    }
}
