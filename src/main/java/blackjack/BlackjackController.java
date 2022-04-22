package blackjack;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BlackjackController {
    @FXML TextField bet;
    @FXML TextArea card1, card2, card3, card4, card5, card6, card7, dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7, balance, totalBet;
    @FXML Label playerScore, dealerScore, information;

    private Main blackjack;

    public void initialize() {
        blackjack = new Main();
        updateBalance();
    }

    public void hitButton(){
        TextArea[] playerCards = {card1, card2, card3, card4, card5, card6, card7};
        blackjack.playerHit();
        for(int i = 2; i < blackjack.getPlayer().getHand().getCardCount(); i++){
            playerCards[i].setText(String.valueOf(blackjack.getPlayer().getHand().getCard(i)));
        }
        updateScore();
        if(blackjack.getPlayer().getHand().isBust()){
            blackjack.turnFinish();
            updateBalance();
        }
    }

    public void standButton(){
        TextArea[] dealerCards = {dealerCard1, dealerCard2, dealerCard3, dealerCard4, dealerCard5, dealerCard6, dealerCard7};
        blackjack.dealerTurn();
        for(int i = 1; i < blackjack.getDealer().getHand().getCardCount(); i++){
            dealerCards[i].setText(String.valueOf(blackjack.getDealer().getHand().getCard(i)));
        }
        blackjack.turnFinish();
        updateScore();
        updateBalance();
    }

    public void dealButton(){
        clearCards();
        setBet();
        updateBalance();
        blackjack.dealHands();
        card1.setText(String.valueOf(blackjack.getPlayer().getHand().getCard(0)));
        card2.setText(String.valueOf(blackjack.getPlayer().getHand().getCard(1)));
        dealerCard1.setText(String.valueOf(blackjack.getDealer().getHand().getCard(0)));
        updateScore();
        if(blackjack.getPlayer().getHand().isBlackjack()){
            standButton();
        }
    }

    public void doubleButton(){
        blackjack.getPlayer().doubleBet();
        hitButton();
        standButton();
    }

    public void setBet(){
        try {
            int currentBet = Integer.parseInt(bet.getText());
            blackjack.getPlayer().placeBetsize(currentBet);
        } catch (NumberFormatException e) {
            information.setText("Bet amount must be a positive number");
        }
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
}