package blackjack;

import java.io.FileNotFoundException;

public interface FileHandler {
    public void writePlayerStatsToFile(String filenamem, Main main);

	public Main getPlayerStatsFromFile(String filename) throws FileNotFoundException;
}
