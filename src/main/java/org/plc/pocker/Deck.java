package org.plc.pocker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Deck {
    private static final int MAX_CARDS_BY_SUIT = 13;
    private List<Suit> validSuits = Arrays.asList(Suit.Club, Suit.Diamond, Suit.Heart, Suit.Spade);
    private final List<Card> cards;
    private int current;

    public Deck() {
        List<Card> generated = new ArrayList<>();

        for (Suit suit : validSuits) {
            for (int i = 1; i <= MAX_CARDS_BY_SUIT; i++) {
                Card card = new Card(String.valueOf(i) + suit.simbol);
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
