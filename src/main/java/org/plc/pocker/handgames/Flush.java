package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Player;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.*;

public class Flush extends AbstractGame{
    private static final BigInteger FLUSH_GAME_WEIGHT = new BigInteger("847");
    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(FLUSH_GAME_WEIGHT.multiply(player.getHand().getHandWeight()));
        List<Card> handCards = player.getHand().getCards();
        Comparator<Card> cardComparator = Collections.reverseOrder(new NumberComparator());
        Collections.sort(handCards, cardComparator);
        List<Card> cards = isHighCardGreaterThanTen(handCards)?new ArrayList<Card>():getLowerCards(handCards);

        player.setCardsToChange(cards);
        return true;
    }

    private List<Card> getLowerCards(List<Card> handCards) {
        return handCards.subList(1,handCards.size());
    }

    private boolean isHighCardGreaterThanTen(List<Card> cards) {
        return cards.get(0).getNumber().intValue()>10;
    }

    @Override
    public boolean isNext(Player player) {
        return !hasAllSameSuit(player.getHand().getCards());
    }
}
