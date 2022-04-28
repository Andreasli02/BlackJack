package blackjack;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StandardFileHandler implements FileHandler{

    @Override
    public void writePlayerStatsToFile(String filename, Main main){
        try {
			PrintWriter writer = new PrintWriter(filename);
            writer.println(main.getPlayer());
            // writer.flush();
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    @Override
    public Main getPlayerStatsFromFile(String filename) throws FileNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
