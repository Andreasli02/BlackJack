package blackjack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
    public String getPlayerStatsFromFile(String filename){
        ArrayList<String> stats = new ArrayList<String>();
        try{
            File myFile = new File(filename);
            Scanner newScanner = new Scanner(myFile);
            while(newScanner.hasNext()){
                String playerStats = newScanner.nextLine();
                stats.add(playerStats);
            }
            newScanner.close();
        }
        catch (NoSuchElementException e){
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        String statsAsString = String.join("\n",stats);
        return statsAsString;
    }
}
