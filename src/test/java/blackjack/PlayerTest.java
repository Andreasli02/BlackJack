package blackjack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    private Player player;

    @BeforeEach
	public void setup() {
		player = new Player();
	}

    @Test
    void testPlaceBetsize() {
        assertThrows(NumberFormatException.class, () -> {player.placeBetsize(0);});
        assertThrows(NumberFormatException.class, () -> {player.placeBetsize(2501);});
        

        player.placeBetsize(1000);
        assertEquals(1000, player.getBetsize());
        assertEquals(1500, player.getBalance());
    }

    @Test
    void testBlackjack() {
        player.placeBetsize(100);
        player.blackjack();
        assertEquals(2650, player.getBalance());
    }

    @Test
    void testWin() {
        player.placeBetsize(100);
        player.win();
        assertEquals(2600, player.getBalance());
    }

    @Test
    void testLose() {
        player.placeBetsize(100);
        player.lose();
        assertEquals(2400, player.getBalance());
    }

    @Test
    void testTie() {
        player.placeBetsize(100);
        player.tie();
        assertEquals(2500, player.getBalance());
    }

    @Test
    void testDoubleBet() {
        player.placeBetsize(100);
        player.doubleBet();
        assertEquals(200, player.getBetsize());
        assertEquals(2300, player.getBalance());
    }

    @Test
    void testDoubleMoreThanBalance() {
        player.placeBetsize(1500);
        assertThrows(NumberFormatException.class, () -> {player.doubleBet();});
    }
}
