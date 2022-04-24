package blackjack;

import java.io.FileNotFoundException;

public interface FileHandler {
    public void save(String filename, Main game) throws FileNotFoundException;

	public Main load(String filename) throws FileNotFoundException;
}
