package org.plc.pocker;

import java.math.BigInteger;
import java.security.InvalidParameterException;

public class Card {
    private final String symbol;
    private Suit suit;
    private BigInteger number;

    public Card(String cardString) {
        if (cardString == null || cardString.length() < 2 || cardString.length() > 3) {
            throw new InvalidParameterException("The card sbould has number between 0 - 10 and single letter ");
        }

        suit = Suit.getSuit(cardString.charAt(cardString.length() - 1));
        int position = cardString.length()==3?2:1;
        symbol = cardString.substring(0, position);
        if (symbolIsLetter()) {
            number = getLetterValue(symbol);
        } else {
            number = new BigInteger(symbol);
        }

    }

    private BigInteger getLetterValue(String symbol) {
        switch (symbol){
            case "A": return new BigInteger("14");
            case "K": return new BigInteger("13");
            case "Q": return new BigInteger("12");
            case "J": return new BigInteger("11");
        }
        return null;
    }

    private boolean symbolIsLetter() {
        char symbolChar = 0;
        if (symbol.length() == 1) {
            symbolChar = symbol.charAt(0);
        }
        return (symbolChar <= 48 || symbolChar >= 57) && (symbolChar == 'A' || symbolChar == 'J' || symbolChar == 'Q' || symbolChar == 'K');
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public BigInteger getNumber() {
        return number;
    }

    public void setNumber(BigInteger number) {
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return getSymbol();
    }
}
