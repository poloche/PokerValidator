package org.plc.pocker.handgames;

import org.plc.pocker.Hand;
import org.plc.pocker.WinnerResult;

public class Flush extends AbstractGame{
    @Override
    protected boolean takeResponsibility(Hand hand, WinnerResult winnerResult) {
        return false;
    }

    @Override
    public boolean isNext(Hand hand) {
        return false;
    }
}
