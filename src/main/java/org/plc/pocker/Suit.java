package org.plc.pocker;

public enum Suit {
    Diamond('D'),
    Club('C'),
    Heart('H'),
    Spade('S');

    public final char simbol;

    Suit(char simbol) {
     this.simbol = simbol;
    }

    public static Suit getSuit(char c) {
        switch (c){
            case 'D': return Diamond;
            case 'C': return Club;
            case 'H': return Heart;
            case 'S': return Spade;
            default: return null;
        }
    }
}
