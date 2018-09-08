package org.plc.pocker.comparators;

import org.plc.pocker.Hand;

public class GameWeightComparator implements java.util.Comparator<Hand> {

    @Override
    public int compare(Hand o1, Hand o2) {
        if (o1.getHandWeight().intValue() == o2.getHandWeight().intValue()) {
            return 0;
        }
        if (o1.getHandWeight().intValue() > o2.getHandWeight().intValue()) {
            return 1;
        }
        return -1;
    }

}
