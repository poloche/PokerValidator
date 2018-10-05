package org.plc.pocker.game;

import org.plc.pocker.Deck;

import java.math.BigInteger;

public abstract class GameConfiguration {


    public abstract ClassicPokerGameValidator configureWinnerRules();

    public abstract int getNumberOfPlayers();

    public abstract Deck getDeck();

    public abstract int getCardsByPlayer();

    public abstract BigInteger getGameBet();
}
