package org.plc.pocker.comparators;

import org.plc.pocker.Hand;

public class GameWeightComparator implements java.util.Comparator<Hand> {

    @Override
    public int compare(Hand o1, Hand o2) {
        return o1.getGameWeight().compareTo(o2.getGameWeight());
    }
}
