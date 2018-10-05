package org.plc.pocker;

import org.plc.pocker.exceptions.ExceededException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Player {
    private String name;
    private double money;
    private boolean isReady;

    private Hand hand;
    private final UUID id;
    private List<Integer> beds;
    private boolean enabled;
    private List<Card> cardsToChange;

    public Player(String name, double money) {
        id = UUID.randomUUID();
        this.name = name;
        this.money = money;
        isReady = false;
        hand = new Hand();
        enabled = false;
        beds = new ArrayList<>();
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public Hand getHand() {
        return hand;
    }

    public UUID getId() {
        return id;
    }

    public void addBet(int bet) throws ExceededException {
        if (money - bet < 0) {
            throw new ExceededException();
        }
        beds.add(Integer.valueOf(bet));
        enabled = true;
    }

    public List<Integer> getBeds() {
        return beds;
    }

    public void removeCards(List<Card> cards) {
        hand.removeCards(cards);
    }

    public void setOff() {
        enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public boolean canBed(int bed) {
        int totalBeds = 0;
        for (Integer bed1 : beds) {
            totalBeds += bed1;
        }

        return (money - (totalBeds + bed)) >= 0;

    }

    public void setCardsToChange(List<Card> cardsToChange) {
        this.cardsToChange = cardsToChange;
    }

    public List<Card> getCardsToChange() {
        return cardsToChange;
    }

    public boolean canContinue() {
        return isReady;
    }

    public void dennyContinue() {
        isReady = false;
    }

    public void enable() {
        isReady = true;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void checkGame() {

    }
}
