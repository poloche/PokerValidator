package org.plc.pocker.comparators;

import org.plc.pocker.Card;

public class NumberComparator implements java.util.Comparator<Card> {
    @Override
    public int compare(Card o1, Card o2) {
        return o1.getNumber().compareTo(o2.getNumber());
    }
}
