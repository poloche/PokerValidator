package org.plc.pocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private static final int MAX_CARDS_BY_SUIT = 13;
    private final List<Card> cards;
    private int current;

    public Deck() {
        List<Card> generated = new ArrayList<>();

        List<Suit> validSuits = Arrays.asList(Suit.Club, Suit.Diamond, Suit.Heart, Suit.Spade);
        for (Suit suit : validSuits) {
            for (int i = 1; i <= MAX_CARDS_BY_SUIT; i++) {
                String letter = String.valueOf(i);
                if (i == 11) {
                    letter = "J";
                }

                if (i == 12) {
                    letter = "Q";
                }

                if (i == 13) {
                    letter = "K";
                }

                Card card = new Card(letter + suit.simbol);
                generated.add(card);
            }
        }
        Collections.shuffle(generated);
        cards = Collections.unmodifiableList(generated);
        current = 0;
    }

    public void suffle() {
        Collections.shuffle(cards);
    }

    public final Card next() {
        return cards.get(current++);
    }
}
