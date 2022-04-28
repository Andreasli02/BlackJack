package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
    private Main blackjack;

    @BeforeEach
    public void setup(){
        blackjack = new Main();
    }

    @Test
    void testDealerTurn() {
        blackjack.getDealer().getHand().addCard(new Card('S', 10));
        blackjack.dealerTurn();
        assertTrue(blackjack.getDealer().getHand().getScore() >= 17);
        assertTrue(blackjack.getDealer().getHand().getCardCount() > 1);
    }

    @Test
    void testDealHands(){
        blackjack.dealHands();
        assertEquals(2, blackjack.getPlayer().getHand().getCardCount());
        assertEquals(1, blackjack.getDealer().getHand().getCardCount());
    }

    @Test
    void testPlayerHit(){
        blackjack.dealHands();
        blackjack.playerHit();
        assertEquals(3, blackjack.getPlayer().getHand().getCardCount());
        blackjack.playerHit();
        assertEquals(4, blackjack.getPlayer().getHand().getCardCount());
    }

    @Test
    void testPlayerDouble(){
        blackjack.dealHands();
        blackjack.getPlayer().placeBetsize(500);
        blackjack.playerDouble();
        assertEquals(1000, blackjack.getPlayer().getBetsize());
        assertEquals(3, blackjack.getPlayer().getHand().getCardCount());
    }

    @Test
    void testTurnFinishPlayerBust() {
        blackjack.getPlayer().placeBetsize(500);
        blackjack.getPlayer().getHand().addCard(new Card('S', 10));
        blackjack.getPlayer().getHand().addCard(new Card('S', 11));
        blackjack.getPlayer().getHand().addCard(new Card('S', 5));
        blackjack.turnFinish();
        assertEquals(2000, blackjack.getPlayer().getBalance());
    }

    @Test
    void testTurnFinishPlayerBlackjack() {
        blackjack.getPlayer().placeBetsize(500);
        blackjack.getPlayer().getHand().addCard(new Card('S', 10));
        blackjack.getPlayer().getHand().addCard(new Card('S', 1));
        blackjack.turnFinish();
        assertEquals(3250, blackjack.getPlayer().getBalance());
    }

    @Test
    void testTurnFinishTie() {
        blackjack.getPlayer().placeBetsize(500);
        blackjack.getPlayer().getHand().addCard(new Card('S', 10));
        blackjack.getPlayer().getHand().addCard(new Card('S', 1));
        blackjack.getDealer().getHand().addCard(new Card('H', 12));
        blackjack.getDealer().getHand().addCard(new Card('H', 1));
        blackjack.turnFinish();
        assertEquals(2500, blackjack.getPlayer().getBalance());
    }

    @Test
    void testTurnFinishPlayerWin() {
        blackjack.getPlayer().placeBetsize(500);
        blackjack.getPlayer().getHand().addCard(new Card('S', 9));
        blackjack.getPlayer().getHand().addCard(new Card('S', 10));
        blackjack.getDealer().getHand().addCard(new Card('H', 10));
        blackjack.getDealer().getHand().addCard(new Card('H', 8));
        blackjack.turnFinish();
        assertEquals(3000, blackjack.getPlayer().getBalance());
    }
}
