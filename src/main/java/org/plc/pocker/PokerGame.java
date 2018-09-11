package org.plc.pocker;

import org.plc.pocker.comparators.GameWeightComparator;
import org.plc.pocker.handgames.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerGame {
    private List<Hand> hands;
    private WinnerResult winnerResult;
    private RoyalFlush royalFlush;

    public PokerGame() {
        this.hands = new ArrayList<>();
        configureGameRules();
        winnerResult = new WinnerResult();
    }

    public void addHand(String stringHand) {
        hands.add(new Hand(stringHand));
    }

    private void configureGameRules() {
        royalFlush = new RoyalFlush();
        StraightFlush straightFlush = new StraightFlush();
        Poker poker = new Poker();
        FullHouse fullHouse = new FullHouse();
        Flush flush = new Flush();
        Straight straight = new Straight();
        Three three = new Three();
        TwoPairs twoPairs = new TwoPairs();
        Pairs pairs = new Pairs();
        HighCard highCard = new HighCard();

        royalFlush.setNext(straightFlush);
        straightFlush.setNext(poker);
        poker.setNext(fullHouse);
        fullHouse.setNext(flush);
        flush.setNext(straight);
        straight.setNext(three);
        three.setNext(twoPairs);
        twoPairs.setNext(pairs);
        pairs.setNext(highCard);

    }

    public List<Hand> getWinnerByWeight() {
        if (hands.isEmpty()) {
            System.out.println("We need at least one hand");
            return null;
        }

        if (hands.size() == 1)
            return hands;

        checkGames();
        Comparator<Hand> comparator = Collections.reverseOrder(new GameWeightComparator());
        Collections.sort(hands, comparator);
        return hands;
    }

    private void checkGames() {

        for (Hand hand : hands) {
            royalFlush.checkGame(hand);
        }
    }
}
