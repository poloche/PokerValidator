package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public interface WhichHand {
    public void setNext(WhichHand winner);
    public WhichHand getNext();
    public boolean checkGame(Hand hand, WinnerResult winnerResult);

}
