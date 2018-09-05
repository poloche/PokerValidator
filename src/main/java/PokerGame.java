import java.util.ArrayList;
import java.util.List;

public class PokerGame {
    List<Hand> hands;
    Hand winner;

    public PokerGame() {
        this.hands = new ArrayList<Hand>();
    }

    public void addHand(String stringHand) {
        hands.add(new Hand(stringHand));
    }

    public Hand getWinner() {
        if (hands.isEmpty()) {
            System.out.println("We need at least one hand");
            return null;
        }

        if (hands.size() == 1)
            return hands.get(0);

        return checkGames();
    }

    private Hand checkGames() {
        for (Hand hand : hands) {
            if(hand.isRoyalFlush()){
                return hand;
            }
        }
        return null;
    }
}
