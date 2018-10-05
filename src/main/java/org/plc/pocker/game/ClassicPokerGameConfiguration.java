package org.plc.pocker.game;

import org.plc.pocker.Deck;

import java.math.BigInteger;

public class ClassicPokerGameConfiguration extends GameConfiguration {
    Deck deck;

    @Override
    public ClassicPokerGameValidator configureWinnerRules() {
        return new ClassicPokerGameValidator();
    }

    @Override
    public int getNumberOfPlayers() {
        return 2;
    }

    @Override
    public Deck getDeck() {
        return deck == null ? new Deck() : deck;
    }

    @Override
    public int getCardsByPlayer() {
        return 5;
    }

    @Override
    public BigInteger getGameBet() {
        return new BigInteger("10");
    }
}
