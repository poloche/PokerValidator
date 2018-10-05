package org.plc.pocker.game;

import org.plc.pocker.Player;
import org.plc.pocker.comparators.GameWeightComparator;
import org.plc.pocker.handgames.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassicPokerGameValidator {

    private List<Player> players;

    ClassicPokerGameValidator() {
    }

    public void setPlayers(List<Player> players) {
        this.players = Collections.unmodifiableList(players);
    }

    private AbstractGame configureGameRules() {
        RoyalFlush royalFlush = new RoyalFlush();
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
        return royalFlush;
    }

    public final List<Player> getWinnerByWeight() {
        if (players.isEmpty()) {
            System.out.println("We need at least one player");
            return null;
        }

        if (players.size() == 1)
            return players;

        checkGames();
        Comparator<Player> comparator = Collections.reverseOrder(new GameWeightComparator());
        Collections.sort(players, comparator);
        return players;
    }

    private void checkGames() {
        AbstractGame start = configureGameRules();
        for (Player player : players) {
            start.checkGame(player);
        }
    }
}
