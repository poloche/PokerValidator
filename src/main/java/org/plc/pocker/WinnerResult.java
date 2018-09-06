package org.plc.pocker;

import java.util.ArrayList;
import java.util.List;

public class WinnerResult {

    private List<Hand> royalFlush = new ArrayList<>();
    private List<Hand> pokers = new ArrayList<>();
    private List<Hand> fulls = new ArrayList<>();
    private List<Hand> threes = new ArrayList<>();
    private List<Hand> twoPairs = new ArrayList<>();
    private List<Hand> pairs = new ArrayList<>();
    private List<Hand> highCards = new ArrayList<>();

    public List<Hand> getRoyalFlush() {
        return royalFlush;
    }

    public void addRoyalWinner(Hand royalFlush) {
        this.royalFlush.add(royalFlush);
    }

    public List<Hand> getPokers() {
        return pokers;
    }

    public void addPokerWinner(Hand poker) {
        this.pokers.add(poker);
    }

    public List<Hand> getFulls() {
        return fulls;
    }

    public void addFullWinner(Hand full) {
        this.fulls.add(full);
    }

    public List<Hand> getTwoPairs() {
        return twoPairs;
    }

    public void addTwoPairWinner(Hand twoPair) {
        this.twoPairs.add(twoPair);
    }

    public List<Hand> getPairs() {
        return pairs;
    }

    public void addPairWinner(Hand pair) {
        this.pairs.add(pair);
    }

    public List<Hand> getHighCards() {
        return highCards;
    }

    public void addHighCard(Hand highCard) {
        this.highCards.add(highCard);
    }

    public List<Hand> getThrees() {
        return threes;
    }

    public void addThreeWinner(Hand three) {
        this.threes.add(three);
    }

    public List<Hand> getWinners() {
        if (!royalFlush.isEmpty()) {
            return royalFlush;
        }
        if (!pokers.isEmpty()) {
            return pokers;
        }

        if (!fulls.isEmpty()) {
            return fulls;
        }

        if (!threes.isEmpty()) {
            return threes;
        }

        if (!twoPairs.isEmpty()) {
            return twoPairs;
        }

        if (!pairs.isEmpty()) {
            return pairs;
        }
        return highCards;
    }
}
