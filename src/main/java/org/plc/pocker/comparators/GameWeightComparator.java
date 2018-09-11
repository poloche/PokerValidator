package org.plc.pocker.comparators;

import org.plc.pocker.Hand;

public class GameWeightComparator implements java.util.Comparator<Hand> {

    @Override
    public int compare(Hand o1, Hand o2) {
        return Integer.compare(o1.getGameWeight().intValue(), o2.getGameWeight().intValue());
    }

}
