package org.plc.pocker;

import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand {
    private List<Card> cards;
    private Map<String, List<Card>> mapCards;
    private BigInteger gameWeight;

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
    }

    public Hand() {
        cards = new ArrayList<>();
    }

    public void sortCardsInMap() {
        mapCards = new HashMap<>();
        for (Card card : cards) {
            if (!mapCards.containsKey(card.getSymbol())) {
                mapCards.put(card.getSymbol(), new ArrayList<Card>());
            }
            mapCards.get(card.getSymbol()).add(card);
        }
    }


    public BigInteger getHandWeight() {
        BigInteger sumValue = new BigInteger("0");
        for (Card card : cards) {
            sumValue = sumValue.add(new BigInteger(String.valueOf(card.getNumber())));
        }
        return sumValue;
    }


    public List<Card> getCards() {
        return cards;
    }

    public void setGameWeight(BigInteger gameWeight) {
        this.gameWeight = gameWeight;
    }

    public BigInteger getGameWeight() {
        return gameWeight;
    }

    public Map<String, List<Card>> getMapCards() {
        return mapCards;
    }

    public String showCards() {
        StringBuilder handString = new StringBuilder("");
        for (Card card : cards) {
            handString.append(card.getSymbol());
            handString.append(card.getSuit().simbol);
            handString.append(" ");
        }
        return handString.toString().trim();
    }

    public void addCard(Card card) {
        cards.add(card);
        sortCardsInMap();
    }

    public void removeCards(List<Card> cards) {
        cards.removeAll(cards);
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : cards) {
            handString.append(card);
            handString.append(",");
        }
        return handString.toString();
    }
}
