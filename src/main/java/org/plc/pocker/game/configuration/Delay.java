package org.plc.pocker.game.configuration;

public class Delay {
    private int acceptingBeats;
    private int playersChangeCards;
    private int registerPlayers;

    public Delay() {
    }

    public int getAcceptingBeats() {
        return acceptingBeats;
    }

    public void setAcceptingBeats(int acceptingBeats) {
        this.acceptingBeats = acceptingBeats;
    }

    public int getPlayersChangeCards() {
        return playersChangeCards;
    }

    public void setPlayersChangeCards(int playersChangeCards) {
        this.playersChangeCards = playersChangeCards;
    }

    public int getRegisterPlayers() {
        return registerPlayers;
    }

    public void setRegisterPlayers(int registerPlayers) {
        this.registerPlayers = registerPlayers;
    }
}
