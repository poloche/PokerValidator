import java.math.BigInteger;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class Hand {
    public static final int MaxWeight = 60;
    List<Card> cards;

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


    public boolean isRoyalFlush() {
        return hasAllSameSuit() && sumCards() == MaxWeight;
    }

    private Integer sumCards() {
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
