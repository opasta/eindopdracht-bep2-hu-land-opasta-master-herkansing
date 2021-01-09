package nl.hu.bep2.casino.blackjack.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import nl.hu.bep2.casino.blackjack.domain.cards.Hand;

@AllArgsConstructor
@Getter
public class Progress {
    private Long id;
    private String playerName;
    private Long bet;
    private Hand player;
    private Hand dealer;
    private GameStatus status;

    @Override
    public String toString() {
        return "Progress{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", bet=" + bet +
                ", player=" + player +
                ", dealer=" + dealer +
                ", status=" + status +
                '}';
    }
}

