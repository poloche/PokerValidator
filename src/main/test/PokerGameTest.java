import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.plc.pocker.Player;
import org.plc.pocker.PokerGame;
import org.plc.pocker.WinnerResult;
import org.plc.pocker.exceptions.ExceededException;
import org.plc.pocker.exceptions.InvalidGameState;
import org.plc.pocker.game.ClassicPokerGameConfiguration;
import org.plc.pocker.game.PlayerIntelligence;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PokerGameTest {
    private static WinnerResult winnerResult = new WinnerResult();

    @BeforeAll
    public static void startWinner() {
        winnerResult = new WinnerResult();
    }

    @Test
    public void testTwoPlayers() throws ExceededException, InvalidGameState {
        final Player paolo = new Player("Paolo", 100);
        Player rolo = new Player("Rolando", 100);
        ClassicPokerGameConfiguration configuration = new ClassicPokerGameConfiguration();
        final PokerGame game = new PokerGame(configuration);

        game.start();
        game.addPlayer(paolo);
        game.addPlayer(rolo);

        paolo.setHand(game.pickCards(paolo));
        rolo.setHand(game.pickCards(rolo));


        game.payBedToContinue(paolo, 10, PlayerIntelligence.getCards(paolo));
        TimerTask waitForPlayers = new TimerTask() {
            @Override
            public void run() {
                assertEquals(paolo.getId(), game.getWinners().get(0).getId());
            }
        };

        Timer timer = new Timer("No Players registered timeout");

        long delay = 11000L;
        timer.schedule(waitForPlayers, delay);


    }

    @Test
    public void testPokerGame() throws InvalidGameState, ExceededException, InterruptedException {
        ClassicPokerGameConfiguration configuration = new ClassicPokerGameConfiguration();
        PokerGame game = new PokerGame(configuration);
        game.start();

        Player paolo = new Player("Paolo", 100);
        Player fernando = new Player("Fernando", 100);

        game.addPlayer(paolo);
        game.addPlayer(fernando);
        // waiting for pay to see
        Thread.sleep(10000L);
        List<Player> winners = game.getWinners();
        for (Player player:             winners) {
            System.out.println(player.getName());
            System.out.println(player.getHand());
        }

    }
}
