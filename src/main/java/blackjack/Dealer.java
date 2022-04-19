package blackjack;

public class Dealer {
    private Hand dealerHand;

    Dealer(){
        dealerHand = new Hand();
    }

    public Hand getHand(){
        return dealerHand;
    }
    
}