package nl.hu.bep2.casino.blackjack.domain.cards;

public enum Rank {
    ACE(11),
    DEUCE(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    JACK(10),
    QUEEN(10),
    KING(10);

    private int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}
