package org.plc.pocker.game.configuration;

public class ClassicPokerConfiguration {
    private int numberOfPlayers;
    private int dealRounds;
    private int cardsByPlayer;
    private int beat;

    private Delay delay;

    public ClassicPokerConfiguration() {
    }

    public Delay getDelay() {
        return delay;
    }

    public void setDelay(Delay delay) {
        this.delay = delay;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getDealRounds() {
        return dealRounds;
    }

    public void setDealRounds(int dealRounds) {
        this.dealRounds = dealRounds;
    }

    public int getCardsByPlayer() {
        return cardsByPlayer;
    }

    public void setCardsByPlayer(int cardsByPlayer) {
        this.cardsByPlayer = cardsByPlayer;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }
}
