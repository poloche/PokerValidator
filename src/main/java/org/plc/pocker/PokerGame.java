package org.plc.pocker;

import org.plc.pocker.exceptions.ExceededException;
import org.plc.pocker.exceptions.InvalidGameState;
import org.plc.pocker.game.ClassicPokerGameValidator;
import org.plc.pocker.game.GameConfiguration;

import java.math.BigDecimal;
import java.util.*;

public class PokerGame {


    public enum ClassicPokerState {
        STARTED, WAITING_FOR_PLAYERS, DEALING_CARDS, ACCEPTING_BEDS, ABANDONED, FINISHED, REVIEWING_WINNER
    }

    private HashMap<UUID, Player> players;
    private WinnerResult winnerResult;

    private ClassicPokerState currentState;
    private GameConfiguration configuration;
    private BigDecimal maxBetGame = new BigDecimal(0);

    private TimerTask waitForBets = new TimerTask() {
        @Override
        public void run() {
            currentState = ClassicPokerState.FINISHED;
        }
    };

    public PokerGame(GameConfiguration configuration) {

        this.players = new HashMap<>();
        winnerResult = new WinnerResult();
        this.configuration = configuration;
    }

    public void start() {
        currentState = ClassicPokerState.STARTED;
        TimerTask waitForPlayers = new TimerTask() {
            @Override
            public void run() {
                checkForStopGame();
            }
        };

        Timer timer = new Timer("No Players registered timeout");

        long delay = 10000L;
        timer.schedule(waitForPlayers, delay);
        currentState = ClassicPokerState.WAITING_FOR_PLAYERS;
    }

    private void checkForStopGame() {
        if (players.size() == 0) {
            stopGame(0);
        }
    }

    public void addPlayer(Player player) throws ExceededException, InvalidGameState {
        if (ClassicPokerState.WAITING_FOR_PLAYERS.equals(currentState)) {
            if (player.getMoney() > configuration.getGameBet().doubleValue() && players.size() <= configuration.getNumberOfPlayers()) {
                player.addBet(configuration.getGameBet().intValue());
                player.enable();
                players.put(player.getId(), player);
                if (configuration.getNumberOfPlayers() == players.size()) {
                    currentState = ClassicPokerState.DEALING_CARDS;
                    dealCards();
                }
            }
        } else {
            if (!ClassicPokerState.STARTED.equals(currentState)) {
                throw new InvalidGameState(ClassicPokerState.STARTED, currentState);
            } else {
                System.out.println("Can't add more players the game was already started");
            }
        }
    }

    private void dealCards() {
        if (ClassicPokerState.DEALING_CARDS.equals(currentState)) {
            for (Player player : players.values()) {
                for (int cardNumber = 0; cardNumber < configuration.getCardsByPlayer(); cardNumber++) {
                    player.addCard(configuration.getDeck().next());
                }
            }
            currentState = ClassicPokerState.ACCEPTING_BEDS;
            Timer timer = new Timer("No Players registered timeout");

            long delay = 10000L;
            timer.schedule(waitForBets, delay);

        } else {
            System.out.println("cannot deal cards the game is " + currentState);
        }
    }

    public Hand pickCards(Player player) {
        if (ClassicPokerState.ACCEPTING_BEDS.equals(currentState)) {
            Player playerInGame = players.get(player.getId());
            if (playerInGame != null) {
                return playerInGame.getHand();
            }
            System.out.println("The player is not registered and does not have a cards");
        } else {
            System.out.println("The game is in status " + currentState + " and is not accepting bets");
        }
        return null;
    }

    public void payBedToContinue(Player player, int bed, List<Card> cards) throws ExceededException {
        if (ClassicPokerState.ACCEPTING_BEDS.equals(currentState)) {
            Player playerInGame = players.get(player.getId());
            if (playerInGame != null && playerInGame.canContinue() && cards != null && cards.size() > 0) {
                if (canGo(playerInGame, bed)) {
                    playerInGame.addBet(bed); //we should store the beds
                    int numberOfCardsToChange = cards.size();
                    playerInGame.dennyContinue();
                    playerInGame.removeCards(cards);
                    for (int cardsToChange = 0; cardsToChange <= numberOfCardsToChange; cardsToChange++) {
                        player.addCard(configuration.getDeck().next());
                    }
                } else {
                    playerInGame.setOff();
                }
            } else {
                System.out.println("only registered players can bed and continue");
            }
        } else {
            System.out.println("The game is in status " + currentState + " and is not accepting bets");
        }
    }

    private boolean canGo(Player playerInGame, int bet) throws ExceededException {
        BigDecimal playerBet = BigDecimal.valueOf(bet);
        if (maxBetGame.intValue() == 0) {
            maxBetGame = playerBet;
        }

        if (maxBetGame.intValue() == bet) {
            playerInGame.dennyContinue();
            playerInGame.addBet(bet);
            return true;
        }

        if (maxBetGame.intValue() > bet) {
            int difference = bet - maxBetGame.intValue();
            maxBetGame = playerBet;
            askJoinedPlayers(playerInGame, difference);
        }
        return false;
    }

    private void askJoinedPlayers(Player playerInGame, int bet) throws ExceededException {
        for (Player player : players.values()) {
            if (player.isEnabled() && player.canBed(bet)) {
                player.addBet(bet);
            }
        }
    }

    private void stopGame(int stopType) {
        if (stopType == 0) {
            System.out.println("No players subscribed");
            currentState = ClassicPokerState.ABANDONED;
        } else {
            System.out.println("Finishing with a result");
            currentState = ClassicPokerState.FINISHED;
        }
    }


    public List<Player> getWinners() {
        if (ClassicPokerState.FINISHED.equals(currentState)) {
            ClassicPokerGameValidator validator = configuration.configureWinnerRules();
            validator.setPlayers(new ArrayList<>(players.values()));
            return validator.getWinnerByWeight();
        }

        System.out.println("The game is not finished yet and has no winners");
        return null;
    }
}
