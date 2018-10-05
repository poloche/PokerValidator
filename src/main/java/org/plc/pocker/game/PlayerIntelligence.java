package org.plc.pocker.game;

import org.plc.pocker.Card;
import org.plc.pocker.Player;
import org.plc.pocker.handgames.*;

import java.util.List;

public class PlayerIntelligence {

    private static AbstractGame configureGameRules() {
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

    public static List<Card> getCards(Player playerHand){
        AbstractGame game = configureGameRules();
        game.checkGame(playerHand);
        return playerHand.getCardsToChange();
    }

}
