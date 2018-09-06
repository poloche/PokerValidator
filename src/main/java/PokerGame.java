import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PokerGame {
    List<Hand> hands;
    Hand winner;
    List<Hand> royalflush = new ArrayList<>();
    List<Hand> pokers = new ArrayList<>();
    List<Hand> fulls = new ArrayList<>();
    List<Hand> twoPairs = new ArrayList<>();
    List<Hand> pairs = new ArrayList<>();
    public PokerGame() {
        this.hands = new ArrayList<>();
    }

    public void addHand(String stringHand) {
        hands.add(new Hand(stringHand));
    }

    public List<Hand> getWinner() {
        if (hands.isEmpty()) {
            System.out.println("We need at least one hand");
            return null;
        }

        if (hands.size() == 1)
            return hands;

        checkGames();
        return checkRules();
    }

    private List<Hand> checkRules() {
        if(!royalflush.isEmpty()){
            return royalflush;
        }

        if(!pokers.isEmpty()){
            return pokers;
        }

        if(!fulls.isEmpty()){
            return fulls;
        }
        if(!twoPairs.isEmpty()){
            return twoPairs;
        }
        if(!pairs.isEmpty()){
            return pairs;
        }
        return null;
    }

    private void checkGames() {

        for (Hand hand : hands) {
            if(hand.isRoyalFlush()){
                royalflush.add(hand);
            }
            if(hand.isPoker()){
                pokers.add(hand);
                Comparator<Hand> comparator = Collections.reverseOrder(new WeightComparator());
                Collections.sort(pokers, comparator);
            }
            if(hand.isThree()){
                fulls.add(hand);
            }
            if(hand.isTwoPairs()){
                twoPairs.add(hand);
            }
            if(hand.isPair()){
                pairs.add(hand);
            }
        }
    }
}
