package nl.hu.bep2.casino.blackjack;

import nl.hu.bep2.casino.blackjack.application.GameService;
import nl.hu.bep2.casino.blackjack.domain.Game;
import nl.hu.bep2.casino.blackjack.domain.Progress;
import nl.hu.bep2.casino.blackjack.domain.cards.Deck;
import nl.hu.bep2.casino.blackjack.domain.cards.Hand;
import nl.hu.bep2.casino.blackjack.domain.cards.Card;
import nl.hu.bep2.casino.blackjack.domain.cards.Rank;
import nl.hu.bep2.casino.blackjack.domain.cards.Suit;

public class TestMain {
    public static void main(String[] args) {
//        Hand hand = new Hand();
//        hand.add(new Card(Rank.DEUCE, Suit.HEARTS));
//        hand.add(new Card(Rank.ACE, Suit.CLUBS));
//        hand.add(new Card(Rank.ACE, Suit.SPADES));
//        hand.add(new Card(Rank.ACE, Suit.HEARTS));
//         15
//        System.out.println(hand.calculateValue());

//        Deck deck = Deck.generate(1);
//        System.out.println(deck.size());
//
//        Card card = deck.draw();
//        System.out.println(card);
//        System.out.println(deck.size());
//
//        System.out.println();
//
//        deck.shuffle();
//        Card card2 = deck.draw();
//        System.out.println(card2);
//        System.out.println(deck.size());

//        Game game = new Game();
//        game.start("Henkie", 10L);
//        Progress progress = game.getProgress();
//        System.out.println(progress);

  //      Hand playerHand = new Hand();
 //       Hand dealerHand = new Hand();


//        Deck deck = Deck.generate(1);
//        System.out.println(deck);
//        deck.shuffle();

 //       playerHand.add(deck.draw());
 //       playerHand.add(deck.draw());
 //       dealerHand.add(deck.draw());
 //       dealerHand.add(deck.draw());
//

 //       System.out.println(playerHand);
 //       System.out.println(dealerHand);

//        Long doubleBet = (10L * 2);


        //Long obj1 = new Long(124);
//        Long obj2 = new Long(167);
//        int compareValue = doubleBet.compareTo(obj2);

 //       if (compareValue < 0){
 //           System.out.println("object1 is less than object2");
 //       } else{
 //           System.out.println("object1 is greater than object2");
 //       }




                int day = 4;
                switch (day) {
                    case 1:
                        System.out.println("Monday");
                        break;
                    case 2:
                        System.out.println("Tuesday");
                        break;
                    case 3:
                        System.out.println("Wednesday");
                        break;
                    case 4:
                        System.out.println("Thursday");
                        break;
                    case 5:
                        System.out.println("Friday");
                        break;
                    case 6:
                        System.out.println("Saturday");
                        break;
                    case 7:
                        System.out.println("Sunday");
                        break;
                }

        Long bet = 10L;
        int status = 2;



        switch (status) {
            case 1:
                bet = bet * 5;
                System.out.println(bet + "test 1");
                break;
            case 2:
                bet = bet * 2;
                System.out.println(bet + "test 2");
                break;
            case 3:
                bet = 0L;
                System.out.println(bet + "test 3");
                break;
            case 4:
                bet = bet / 2;
                System.out.println(bet + "test 4");
                break;
        }

        System.out.println(bet);




    }
}
