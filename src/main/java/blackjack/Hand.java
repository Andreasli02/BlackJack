package blackjack;
import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<Card>();

    public int getCardCount(){
        return hand.size();
    }

    public Card getCard(int n){
        try{
            return hand.get(n);
        } catch (Exception e) {
            throw new IllegalArgumentException("This card doesn't exist");
        }
    }

    public int getScore(){
        
    }

    public void addCard(Card newCard){
		this.hand.add(newCard);
	}

    public Card play(int n){
		Card inplay = this.hand.get(n);
		this.hand.remove(n);
		return inplay;
	}
}
