package blackjack;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class StandardFileHandler implements FileHandler{

    @Override
    public void writePlayerStatsToFile(String filename, Main main){
        PrintWriter writer = null;
        try{
            FileWriter newWriter = new FileWriter(filename, true);
            writer = new PrintWriter(newWriter);
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        writer.println(main.getPlayer());
        writer.close();
    }

    @Override
    public Main getPlayerStatsFromFile(String filename) throws FileNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }
}
