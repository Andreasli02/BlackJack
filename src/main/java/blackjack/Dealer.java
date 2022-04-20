package blackjack;

public class Dealer {
    private Hand dealerHand;

    Dealer(){
        dealerHand = new Hand();
    }

    public Hand getDealerHand(){
        return dealerHand;
    }

    public String toString(){
        return String.valueOf(dealerHand);
    }
}