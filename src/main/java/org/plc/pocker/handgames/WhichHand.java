package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public interface WhichHand {
    void setNext(WhichHand winner);
    WhichHand getNext();
    boolean checkGame(Hand hand);

}
