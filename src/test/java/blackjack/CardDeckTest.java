package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CardDeckTest {
    private CardDeck deck;

    @BeforeEach
    public void setup(){
        deck = new CardDeck();
    }

    @Test
    void testDeal() {
        Hand hand = new Hand();
        deck.deal(hand, 4);
        assertEquals(4, hand.getCardCount());
        assertEquals(48, deck.getCardCount());
    }

    @Test
    void testShuffleDeck() {
        CardDeck notShuffled = new CardDeck();
        deck.shuffleDeck();
        assertEquals(52, deck.getCardCount());
        assertNotEquals(notShuffled, deck);
        System.out.println(deck);
    }
}
