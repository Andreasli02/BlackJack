package blackjack;

public class Player {
    private int balance;
    private int betSize;
    private Hand playerHand;

    public Player(){
        this.playerHand = new Hand();
        this.balance = 2500;
    }

    
}
