package org.plc.pocker.exceptions;

import org.plc.pocker.PokerGame;

public class InvalidGameState extends Throwable {
    public InvalidGameState(PokerGame.ClassicPokerState expectedState, PokerGame.ClassicPokerState currentState) {
        super("The game is waiting " + expectedState + " state but it is in " + currentState + " state");
    }
}
