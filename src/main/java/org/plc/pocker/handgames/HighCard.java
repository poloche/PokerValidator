package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HighCard extends AbstractGame {
    private static final BigInteger HIGH_CARD_GAME_WEIGHT = new BigInteger("1");

    @Override
    protected boolean takeResponsibility(Hand hand) {
        if (hand.getMapCards().size() == 5) {
            List<Card> cards = hand.getCards();
            Comparator<Card> cardComparator = Collections.reverseOrder(new NumberComparator());
            Collections.sort(cards, cardComparator);
            hand.setGameWeight(HIGH_CARD_GAME_WEIGHT.multiply(cards.get(0).getNumber()));
            return true;
        }
        return false;
    }

    @Override
    public boolean isNext(Hand hand) {
        return false;
    }
}
