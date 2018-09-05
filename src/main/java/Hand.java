import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.*;

public class Hand {
    public static final int MaxWeight = 60;
    private List<Card> cards;
    private Map<String, List<Card>> mapCards;

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
            if (mapCards.containsKey(card.getSymbol())) {
                List<Card> cards = new ArrayList<>(mapCards.get(card.getSymbol()));
                cards.add(card);
                mapCards.put(card.getSymbol(), cards);
            } else {
                List<Card> cards = Collections.singletonList(card);
                mapCards.put(card.getSymbol(), cards);
            }
        }
    }


    public boolean isRoyalFlush() {
        return hasAllSameSuit() && getHandWeight() == MaxWeight;
    }

    public boolean isPoker() {
        return mapCards.size() == 2 && !isThree();
    }

    public boolean isThree() {
        if(mapCards.size() == 2){
            for (Map.Entry<String, List<Card>> entry : mapCards.entrySet()) {
                if (entry.getValue().size() == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isTwoPairs() {
        if (mapCards.size() == 3) {
            int count = 0;
            for (Map.Entry<String, List<Card>> entry : mapCards.entrySet()) {
                if (entry.getValue().size() == 2) {
                    count++;
                }
            }
            return count == 2;
        }
        return false;
    }

    public boolean isPair() {
        return mapCards.size() == 4;
    }

    private Integer getHandWeight() {
        BigInteger sumValue = new BigInteger("0");
        for (Card card : cards) {
            sumValue = sumValue.add(new BigInteger(String.valueOf(card.getNumber())));
        }
        return sumValue.intValue();
    }

    private boolean hasAllSameSuit() {
        Suit suit = null;
        for (Card card : cards) {
            if (suit == null) {
                suit = card.getSuit();
            } else {
                if (!suit.equals(card.getSuit())) {
                    return false;
                }
            }
        }
        return true;
    }


}
