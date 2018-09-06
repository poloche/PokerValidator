package org.plc.pocker;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.*;

public class Hand {
    private List<Card> cards;
    private Map<String, List<Card>> mapCards;
    private int game;

    public Hand(String handString) {
        if (handString == null) {
            throw new InvalidParameterException("Invalid hand, should not be null");
        }
        String[] arrayCards = handString.split(" ");
        if (arrayCards.length != 5) {
            throw new InvalidParameterException("Invalid hand, should not has 5 cards representation");
        }
        cards = new ArrayList<>();
        for (String card : arrayCards) {
            cards.add(new Card(card));
        }

        mapCards = new HashMap<>();
        for (Card card : cards) {
            if (!mapCards.containsKey(card.getSymbol())) {
                mapCards.put(card.getSymbol(), new ArrayList<Card>());
            }
            mapCards.get(card.getSymbol()).add(card);
        }
    }




    public Integer getHandWeight() {
        BigInteger sumValue = new BigInteger("0");
        for (Card card : cards) {
            sumValue = sumValue.add(new BigInteger(String.valueOf(card.getNumber())));
        }
        return sumValue.intValue();
    }




    public List<Card> getCards() {
        return cards;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public Map<String, List<Card>> getMapCards() {
        return mapCards;
    }
}
