package org.plc.pocker.game;

import org.plc.pocker.Deck;
import org.plc.pocker.game.configuration.ClassicPokerConfiguration;

import java.math.BigInteger;

public class ClassicPokerGameConfiguration extends GameConfiguration<ClassicPokerConfiguration> {
    public static final long WAITING_FOR_PLAYERS_DELAY = 10000L;
    public static final long ACCEPTING_BEDS_DELAY = 1000L;
    public static final long PAY_TO_SEE_DELAY = 1000L;
    private Deck deck;
    private ClassicPokerConfiguration configuration;
    public ClassicPokerGameConfiguration(){
        super("GameConfiguration.yaml", new ClassicPokerConfiguration());
        configuration = getConfiguration();
    }


    public ClassicPokerGameValidator configureWinnerRules() {
        return new ClassicPokerGameValidator();
    }


    public int getNumberOfPlayers() {
        return configuration.getNumberOfPlayers();
    }


    public Deck getDeck() {
        if (deck == null) {
            deck = new Deck();
        }
        return deck;
    }


    public int getCardsByPlayer() {
        return configuration.getCardsByPlayer();
    }


    public BigInteger getGameBet() {
        return new BigInteger(String.valueOf(configuration.getBeat()));
    }


    public int getMaxDealRounds() {
        return configuration.getDealRounds();
    }
}
