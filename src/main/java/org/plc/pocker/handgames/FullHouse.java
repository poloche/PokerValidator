package org.plc.pocker.handgames;

import org.plc.pocker.Card;
import org.plc.pocker.Hand;
import org.plc.pocker.Player;
import org.plc.pocker.comparators.NumberComparator;

import java.math.BigInteger;
import java.util.*;

public class FullHouse extends AbstractGame {
    private static final BigInteger POKER_GAME_WEIGHT = new BigInteger("3812");

    @Override
    protected boolean takeResponsibility(Player player) {
        player.getHand().setGameWeight(POKER_GAME_WEIGHT.multiply(player.getHand().getHandWeight()));
        List<Card> handCards = player.getHand().getCards();
        List<Card> cards = isGoodHighCard(handCards) ? new ArrayList<Card>() : getLowerCards(handCards);

        player.setCardsToChange(cards);

        return true;
    }

    private List<Card> getLowerCards(List<Card> handCards) {
        Comparator<Card> cardComparator = Collections.reverseOrder(new NumberComparator());
        Collections.sort(handCards, cardComparator);

        int max = handCards.get(0).getNumber().intValue();
        List<Card> lowerCards = new ArrayList<>();
        for (Card card : handCards) {
            if (card.getNumber().intValue() != max) {
                lowerCards.add(card);
            }
        }
        return lowerCards;
    }

    private boolean isGoodHighCard(List<Card> cards) {
        Comparator<Card> cardComparator = Collections.reverseOrder(new NumberComparator());
        Collections.sort(cards, cardComparator);

        return cards.get(0).getNumber().intValue() > 10;
    }

    @Override
    public boolean isNext(Player player) {
        return !isFull(player.getHand());
    }

    private boolean isFull(Hand hand) {
        boolean hasPair = false;
        boolean hasThree = false;
        if (hand.getMapCards().size() == 2) {
            for (Map.Entry<String, List<Card>> entry : hand.getMapCards().entrySet()) {
                if (entry.getValue().size() == 2) {
                    hasPair = true;
                }
                if (entry.getValue().size() == 3) {
                    hasThree = true;
                }
            }
        }
        return hasPair && hasThree;
    }
}
