package org.plc.pocker.comparators;

import org.plc.pocker.Player;

public class GameWeightComparator implements java.util.Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        return Integer.compare(o1.getHand().getGameWeight().intValue(), o2.getHand().getGameWeight().intValue());
    }

}
