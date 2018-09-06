package org.plc.pocker;

public class WeightComparator implements java.util.Comparator<Hand> {

    @Override
    public int compare(Hand o1, Hand o2) {
        return o1.getHandWeight().compareTo(o2.getHandWeight());
    }
}
