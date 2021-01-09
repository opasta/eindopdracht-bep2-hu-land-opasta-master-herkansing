package nl.hu.bep2.casino.blackjack.domain.cards;

import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Hand implements Serializable {
    private List<Card> cards = new ArrayList<>();

    public void add(Card card) {
        cards.add(card);
    }

    public Hand showUpCard() {
        Hand newHand = new Hand();
        newHand.cards.add(this.cards.get(0));
        return newHand;
    }

    public int calculateValue() {
        int totalValue = 0;
        int numberOfAces = 0;

        // Sum of all card values
        // and keep track of aces
        for (Card card : this.cards) {
            if (card.isAce()) {
                numberOfAces++;
            }

            totalValue += card.getValue();
        }

        // Subtract aces to get the
        // highest value under 22
        while (numberOfAces > 0) {
            if (totalValue > 21) {
                totalValue -= 10;
            }

            numberOfAces--;
        }

        return totalValue;
    }

    @Override
    public String toString() {
        return "Hand{" +
                "cardList=" + cards +
                '}';
    }
}
