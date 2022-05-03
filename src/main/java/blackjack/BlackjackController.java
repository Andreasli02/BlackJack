package blackjack;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BlackjackController {
    @FXML TextField bet;
    @FXML TextArea card1, card2, card3, card4, card5, card6, card7, dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7, balance, totalBet, playerStats;
    @FXML Label playerScore, dealerScore, information;
    @FXML Button hitButton, standButton, doubleButton, dealButton, statsButton;

    private Main blackjack;
    private StandardFileHandler fileHandler;

    public void initialize() {
        //create playerStats.txt and clears it
        try (PrintWriter pw = new PrintWriter("PlayerStats.txt")) {
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        blackjack = new Main();
        fileHandler = new StandardFileHandler();
        updateBalance();
        disableActionButtons();
        handleWritePlayerStats();
        playerStats.setVisible(false);
    }

    public void hitButton(){
        TextArea[] playerCards = {card1, card2, card3, card4, card5, card6, card7};
        blackjack.playerHit();
        for(int i = 2; i < blackjack.getPlayer().getHand().getCardCount(); i++){
            playerCards[i].setText(String.valueOf(blackjack.getPlayer().getHand().getCard(i)));
        }
        updateScore();
        doubleButton.setDisable(true); 
        if(blackjack.getPlayer().getHand().isBust()){
            restartGameState();
        }
    }

    public void standButton(){
        TextArea[] dealerCards = {dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7};
        blackjack.dealerTurn();
        for(int i = 1; i < blackjack.getDealer().getHand().getCardCount(); i++){
            dealerCards[i].setText(String.valueOf(blackjack.getDealer().getHand().getCard(i)));
        }
        updateScore();
        restartGameState();
    }

    public void dealButton(){
        try{
            setBet();
        }
        catch (NumberFormatException e) {
            information.setText("Bet amount must be between 1 and your balance!");
            return;
        }
        information.setText("Good luck!");
        enableActionButtons();
        clearCards();
        updateBalance();
        blackjack.dealHands();

        card1.setText(String.valueOf(blackjack.getPlayer().getHand().getCard(0)));
        card2.setText(String.valueOf(blackjack.getPlayer().getHand().getCard(1)));
        dealerCard1.setText(String.valueOf(blackjack.getDealer().getHand().getCard(0)));

        updateScore();
        dealButton.setDisable(true);
        
        if(blackjack.getPlayer().getHand().isBlackjack()){
            blackjack.dealerGetCard();
            dealerCard2.setText(String.valueOf(blackjack.getDealer().getHand().getCard(1)));
            blackjack.turnFinish();
            updateBalance();
            disableActionButtons();
            information.setText("Congratulations you got blackjack!");
            dealButton.setDisable(false);
            handleWritePlayerStats();
            handleGetPlayerStats();
        }
    }

    public void doubleButton(){
        try{
            blackjack.getPlayer().doubleBet();
        } catch (NumberFormatException e){
            information.setText("you cannot bet more than you own");
            return;
        }
        hitButton();
        standButton();
    }

    public void statsButton(){
        if(playerStats.isVisible()){
            playerStats.setVisible(false);
        }
        else{
            playerStats.setVisible(true);
        }
    }

    public void disableActionButtons(){
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleButton.setDisable(true);
    }

    public void enableActionButtons(){
        hitButton.setDisable(false);
        standButton.setDisable(false);
        doubleButton.setDisable(false);
    }

    public void setBet(){
        int currentBet = Integer.parseInt(bet.getText());
        blackjack.getPlayer().placeBetsize(currentBet);
    }

    public void updateScore(){
        dealerScore.setText(String.valueOf(blackjack.getDealer().getHand().getScore()));
        playerScore.setText(String.valueOf(blackjack.getPlayer().getHand().getScore()));
    }

    public void updateBalance(){
        balance.setText(String.valueOf(blackjack.getPlayer().getBalance()));
        totalBet.setText(String.valueOf(blackjack.getPlayer().getBetsize()));
    }

    public void clearCards(){
        TextArea[] playerCards = {card1, card2, card3, card4, card5, card6, card7};
        TextArea[] dealerCards = {dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7};
        for(int i = 0; i < 7; i++){
            playerCards[i].setText("");
            dealerCards[i].setText("");
        }
    }

    public void gameOver(){
        information.setText("You lost! Try again another time");
        hitButton.setDisable(true);
        standButton.setDisable(true);
        doubleButton.setDisable(true);
        dealButton.setDisable(true);
    }

    public void restartGameState(){
        blackjack.turnFinish();
        updateBalance();
        disableActionButtons();
        dealButton.setDisable(false);
        handleWritePlayerStats();
        handleGetPlayerStats();
        if(blackjack.getPlayer().getBalance() == 0){
            gameOver();
        }
    }

    public void handleWritePlayerStats(){
        fileHandler.writePlayerStatsToFile("PlayerStats.txt", blackjack);            
    }

    public void handleGetPlayerStats(){
        playerStats.setText(fileHandler.getPlayerStatsFromFile("PlayerStats.txt"));
    }
}