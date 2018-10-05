package org.plc.pocker.handgames;

import org.plc.pocker.Player;

public interface WhichHand {
    void setNext(WhichHand winner);
    WhichHand getNext();
    boolean checkGame(Player player);

}
