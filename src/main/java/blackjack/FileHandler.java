package blackjack;

public interface FileHandler {
    public void writePlayerStatsToFile(String filenamem, Main main);

	public String getPlayerStatsFromFile(String filename);
}
