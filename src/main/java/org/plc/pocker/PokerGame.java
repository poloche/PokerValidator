package org.plc.pocker;

import org.plc.pocker.exceptions.ExceededException;
import org.plc.pocker.exceptions.InvalidGameState;
import org.plc.pocker.game.ClassicPokerGameValidator;
import org.plc.pocker.game.GameConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.math.BigDecimal;
import java.util.*;

public class PokerGame {
Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final long WAITING_FOR_PLAYERS_DELAY = 1000L;
    private static final long ACCEPTING_BEDS_DELAY = 1000L;
    private static final long PAY_TO_SEE_DELAY = 1000L;
    private int globalBet = 0;
    private int currentDealRound = 0;

    private Timer backgroundTask = new Timer("Executing background task");

    public enum ClassicPokerState {
        OPEN("Waiting for players"),
        FULL("Maximum players registered"),
        STARTED("Dealer is mixing cards"),
        DEALING("First round dealing cards"),
        WAITING_FOR_PLAYERS("Waiting for players to change cards "),
        WAITING_FOR_PAY_TO_SEE("If some player Pay to see the other cards"),
        ACCEPTING_BEDS("If some player while changing cards wants to bed"),
        REVIEWING_WINNER("Final round or bed based review cards"),
        FINISHED("show winners"),
        WAITING_FOR_PLAYERS_CHANGE_CARDS("When the game is wating users change cards in some round");

        private final String description;

        ClassicPokerState(String description) {
            this.description = description;
        }
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

    private TimerTask waitForPyToSee = new TimerTask() {
        @Override
        public void run() {
            LOGGER.info("PayToSee timeout, waiting some more time for Players change cards");
            currentState = ClassicPokerState.WAITING_FOR_PLAYERS_CHANGE_CARDS;
            backgroundTask.schedule(waitingForCardChange, WAITING_FOR_PLAYERS_DELAY);
        }
    };

    private TimerTask waitForPlayers = new TimerTask() {
        @Override
        public void run() {
            LOGGER.info("No more players, check for start or finish the game");
            checkForStopGame();
        }
    };

    private TimerTask waitingForCardChange = new TimerTask() {
        @Override
        public void run() {
            boolean runChangeCard = false;
            for (Player player : players.values()) {
                if(player.requireCardsChange()){
                    runChangeCard = true;
                }
            }
            if(runChangeCard){
                LOGGER.info("Some changes are required, going to change cards");
                changeCards();
            }else {
                LOGGER.info("Players does not required card changes, finishing the game");
                stopGame(0);
            }
        }
    };

    private void changeCards() {

    }

    public PokerGame(GameConfiguration configuration) {

        this.players = new HashMap<>();
        winnerResult = new WinnerResult();
        this.configuration = configuration;
    }

    /**
     * Open the game and set to it ready to accept players
     */
    public void start() {
        currentState = ClassicPokerState.OPEN;
        LOGGER.info("Starting the game");
        backgroundTask.schedule(waitForPlayers, WAITING_FOR_PLAYERS_DELAY);
        currentState = ClassicPokerState.WAITING_FOR_PLAYERS;
    }

    private void checkForStopGame() {
        if (players.isEmpty()) {
            stopGame(0);
        } else {
            LOGGER.info("We have {} players registered, starting deal", players.size());
            dealCards();
        }
    }

    public void addPlayer(Player player) throws ExceededException, InvalidGameState {
        if (ClassicPokerState.WAITING_FOR_PLAYERS.equals(currentState)) {
            if (player.getMoney() > configuration.getGameBet().doubleValue() && players.size() <= configuration.getNumberOfPlayers()) {
                player.addBet(configuration.getGameBet().intValue());
                player.enable();
                players.put(player.getId(), player);
                if (configuration.getNumberOfPlayers() == players.size()) {
                    LOGGER.info("Players complete, start dealing");
                    currentState = ClassicPokerState.DEALING;
                    waitForPlayers.cancel();
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

    public void checkPayToSee(Player player) {
        if (ClassicPokerState.WAITING_FOR_PAY_TO_SEE.equals(currentState)) {
            if (isPlayerInTheGame(player) && player.hasPayToSee()) {
                globalBet = player.getLastBet();
                currentState = ClassicPokerState.ACCEPTING_BEDS;

                backgroundTask.schedule(waitForBets, ACCEPTING_BEDS_DELAY);
            }
        } else {
            System.out.println("cannot deal cards the game is " + currentState);
        }
    }

    private boolean isPlayerInTheGame(Player player) {
        for (Player registeredPlayer : players.values()) {
            if (registeredPlayer.getId().equals(player.getId()) && registeredPlayer.isEnabled()) {
                return true;
            }
        }
        return false;
    }

    private void acceptBets(List<Player> playersWithBet) throws ExceededException {
        if (ClassicPokerState.ACCEPTING_BEDS.equals(currentState)) {
            for (Player player : playersWithBet) {
                if (isPlayerInTheGame(player) && player.getLastBet() == globalBet) {
                    players.get(player.getId()).addBet(player.getLastBet());
                    players.get(player.getId()).setEnabledWithBet(true);
                }
            }
            currentState = ClassicPokerState.DEALING;
            dealCards();
        } else {
            System.out.println("cannot deal cards the game is " + currentState);
        }
    }

    private void dealCards() {
        if (ClassicPokerState.DEALING.equals(currentState) && currentDealRound < configuration.getMaxDealRounds()) {
            currentDealRound++;
            LOGGER.info("dealing round {} ", currentDealRound);
            for (Player player : players.values()) {
                if (player.isEnabledWithBet()) {
                    for (int cardNumber = 0; cardNumber < configuration.getCardsByPlayer(); cardNumber++) {
                        player.addCard(configuration.getDeck().next());
                        players.get(player.getId()).setEnabledWithBet(false);
                    }
                }
            }
            currentState = ClassicPokerState.WAITING_FOR_PAY_TO_SEE;
            backgroundTask.schedule(waitForPyToSee, ACCEPTING_BEDS_DELAY);
        } else {
            if (currentDealRound == configuration.getMaxDealRounds()) {
                currentState = ClassicPokerState.FINISHED;
            }
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

    public void requestCardChange(Player player, List<Card> cards) {
        if (ClassicPokerState.WAITING_FOR_PLAYERS_CHANGE_CARDS.equals(currentState) && isPlayerInTheGame(player)) {

        } else {
            System.out.println("Player cna not change card in " + currentState + " state");
        }
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
            currentState = ClassicPokerState.FINISHED;
            LOGGER.info("finishing by stopType {}, current state {}", stopType, currentState);
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

        LOGGER.info("The game is not finished yet and has no winners, current state {}", currentState);
        return null;
    }
}
