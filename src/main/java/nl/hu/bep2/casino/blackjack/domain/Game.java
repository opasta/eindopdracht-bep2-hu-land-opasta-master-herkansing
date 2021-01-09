package nl.hu.bep2.casino.blackjack.domain;

import lombok.Getter;
import nl.hu.bep2.casino.blackjack.domain.cards.Deck;
import nl.hu.bep2.casino.blackjack.domain.cards.Hand;

import javax.persistence.*;

@Entity
@Getter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String playerName;
    private Long bet;

    @Lob
    private Deck deck;

    @Lob
    private Hand playerHand = new Hand();

    @Lob
    private Hand dealerHand = new Hand();

    private GameStatus status = GameStatus.CREATED;

    public void start(String playerName, Long bet) {
        if (!status.equals(GameStatus.CREATED)) {
            throw new RuntimeException("Cannot start game, already started");
        }
        status = GameStatus.PLAYING;

        this.playerName = playerName;
        this.bet = bet;

        deck = Deck.generate(1);
        deck.shuffle();
        playerHand.add(deck.draw());
        playerHand.add(deck.draw());
        dealerHand.add(deck.draw());
        dealerHand.add(deck.draw());

        int playerHandValue = playerHand.calculateValue();
        int dealerHandValue = dealerHand.calculateValue();
        if (playerHandValue == 21) {
            if (dealerHandValue == 21) {
                status = GameStatus.PUSH;
            } else {
                status = GameStatus.BLACKJACK;
            }
        }

        this.calculateNewBet();
    }

    public void hit(String playerName, Deck deck, Hand playerHand) {
        if (!status.equals(GameStatus.PLAYING)) {
            throw new RuntimeException("Cannot draw card, status isn't playing");
        }
        int playerHandValue = playerHand.calculateValue();
        if (playerHandValue > 21) {
            throw new RuntimeException("allready over 21, pls stop");
        }
        this.playerName = playerName;
        playerHand.add(deck.draw());
    }

    public void surrender(String playerName, Long bet, Hand playerHand) {
        if (!status.equals(GameStatus.PLAYING)) {
            throw new RuntimeException("No game started");
        }
        int playerHandValue = playerHand.calculateValue();
        if (playerHandValue > 21) {
            throw new RuntimeException("Nice try, but you already went over 21");
        }
        status = GameStatus.SURRENDERED;
        this.bet = bet;
        this.playerName = playerName;
        this.calculateNewBet();
    }



    public Progress getProgress() {
        if (this.status.equals(GameStatus.PLAYING)) {
            return new Progress(id, playerName, bet, playerHand, dealerHand.showUpCard(), status);
        }

        return new Progress(id, playerName, bet, playerHand, dealerHand, status);
    }

    private void calculateNewBet() {
        switch (status) {
            case BLACKJACK:
                bet = bet * 5;
                break;
            case WIN:
                bet = bet * 2;
                break;
            case BUST:
                bet = 0L;
                break;
            case SURRENDERED:
                bet = bet / 2;
                break;
        }
    }

    public void calculateDealer(String playerName, Deck deck, Hand playerHand, Hand dealerHand, Long bet) {
        if (!status.equals(GameStatus.PLAYING)) {
            throw new RuntimeException("No game started");
        }

        int playerHandValue = playerHand.calculateValue();
        int dealerHandValue = dealerHand.calculateValue();

        if (playerHandValue > 21 || playerHandValue < 12){
            playerHandValue = 0;
        }

        while(dealerHandValue < 17) {
            dealerHand.add(deck.draw());
            dealerHandValue = dealerHand.calculateValue();
        }

        if (dealerHandValue > 21){
            dealerHandValue = 0;
        }

        if(playerHandValue > dealerHandValue){
            status = GameStatus.WIN;
        }else if(playerHandValue == dealerHandValue){
            status = GameStatus.PUSH;
        }else {
            status = GameStatus.BUST;
        }

        this.playerName = playerName;
        this.bet = bet;
        this.calculateNewBet();
    }
}
