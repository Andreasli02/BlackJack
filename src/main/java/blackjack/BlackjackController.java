package blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BlackjackController {
    @FXML TextField bet;
    @FXML TextArea card1, card2, card3, card4, card5, card6, card7, dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7, balance, totalBet;
    @FXML Label playerScore, dealerScore, information;
    @FXML Button hitButton, standButton, doubleButton, dealButton;

    private Main blackjack;

    public void initialize() {
        blackjack = new Main();
        updateBalance();
        disableActionButtons();
    }

    public void hitButton(){
        TextArea[] playerCards = {card1, card2, card3, card4, card5, card6, card7};
        blackjack.playerHit();
        for(int i = 2; i < blackjack.getPlayer().getHand().getCardCount(); i++){
            playerCards[i].setText(String.valueOf(blackjack.getPlayer().getHand().getCard(i)));
        }
        updateScore();
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
            dealButton.setDisable(false);
        }
    }

    public void doubleButton(){
        blackjack.getPlayer().doubleBet();
        hitButton();
        standButton();
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
        if(blackjack.getPlayer().getBalance() == 0){
            gameOver();
        }
    }
}