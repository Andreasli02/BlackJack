package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class HandTest {
    private Hand hand;

    @BeforeEach
    public void setup(){
        hand = new Hand();
    }

    @Test
    void testClearHand() {
        CardDeck deck = new CardDeck();
        deck.deal(hand, 2);
        hand.clearHand();
        assertEquals(0, hand.getCardCount());
    }

    @Test
    void testGetAceCount() {
        hand.addCard(new Card('S', 1));
        assertEquals(1, hand.getAceCount());
        hand.addCard(new Card('H', 1));
        hand.addCard(new Card('H', 2));
        assertEquals(2, hand.getAceCount());
    }

    @Test
    void testGetScore() {
        hand.addCard(new Card('H', 5));
        assertEquals(5, hand.getScore());

        hand.addCard(new Card('H', 1));
        assertEquals(16, hand.getScore());

        hand.addCard(new Card('H', 13));
        assertEquals(16, hand.getScore());

        hand.addCard(new Card('S', 13));
        assertEquals(26, hand.getScore());
    }

    @Test
    void testIsBlackjack() {
        hand.addCard(new Card('S', 1));
        hand.addCard(new Card('S', 12));
        assertTrue(hand.isBlackjack());
        hand.addCard(new Card('S', 10));
        assertFalse(hand.isBlackjack());
    }

    @Test
    void testIsBlackjackNot21(){
        hand.addCard(new Card('S', 5));
        hand.addCard(new Card('S', 6));
        assertFalse(hand.isBlackjack());
    }

    @Test
    void testIsBust() {
        hand.addCard(new Card('S', 5));
        hand.addCard(new Card('H', 6));
        assertFalse(hand.isBust());
        hand.addCard(new Card('S', 10));
        assertFalse(hand.isBust());
        hand.addCard(new Card('S', 2));
        assertTrue(hand.isBust());
    }
}
