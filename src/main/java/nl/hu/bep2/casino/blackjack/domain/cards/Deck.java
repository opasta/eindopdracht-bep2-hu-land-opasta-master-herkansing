package nl.hu.bep2.casino.blackjack.domain.cards;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@AllArgsConstructor
public class Deck implements Serializable {
    private List<Card> cards = new ArrayList<>();

    public static Deck generate(int decks) {
        List<Card> completeDeck = new ArrayList<>();

        for (int i = 0; i < decks; i++) {
            for (Rank rank : Rank.values()) {
                for (Suit suit : Suit.values()) {
                    completeDeck.add(new Card(rank, suit));
                }
            }
        }

        return new Deck(completeDeck);
    }

    public Card draw() {
        return this.cards.remove(0);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public int size() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
