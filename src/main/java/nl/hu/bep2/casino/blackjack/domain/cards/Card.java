package nl.hu.bep2.casino.blackjack.domain.cards;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
public class Card implements Serializable {
    private Rank rank;
    private Suit suit;

    public int getValue() {
        return this.rank.getValue();
    }

    public boolean isAce() {
        return this.rank.equals(Rank.ACE);
    }

    @Override
    public String toString() {
        return "Card{" +
                "rank=" + rank +
                ", suit=" + suit +
                '}';
    }
}
