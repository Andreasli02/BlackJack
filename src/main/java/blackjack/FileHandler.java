package blackjack;

import java.io.FileNotFoundException;

public interface FileHandler {
    public void writePlayerStatsToFile(String filename) throws FileNotFoundException;

	public Main getPlayerStatsFromFile(String filename) throws FileNotFoundException;
}
