package blackjack;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CardTest {
    @Test
    void testToString() {
        Assertions.assertEquals("♠ A", new Card('S', 1).toString());
        Assertions.assertEquals("♠ 2", new Card('S', 2).toString());
        Assertions.assertEquals("♠ Q", new Card('S', 12).toString());
		Assertions.assertEquals("♥ Q", new Card('H', 12).toString());
        Assertions.assertEquals("♦ Q", new Card('D', 12).toString());
        Assertions.assertEquals("♣ Q", new Card('C', 12).toString());
    }
}
