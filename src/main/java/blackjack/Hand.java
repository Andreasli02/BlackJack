package blackjack;
import java.util.ArrayList;
import java.util.function.Predicate;

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

    public int getCardCount(Predicate<Card> predicate) {
		int count = 0; 
		for (Card card : hand) {
			if (predicate.test(card)) {
				count++;
			}
		}
		return count; 
	}

    public int getScore(){
        int score = 0;

    }

    public boolean bust(){

    }

    public void addCard(Card newCard){
        if(hand.size() >= 7){
            throw new IllegalArgumentException("Hand cannot have more than 7 cards");
        }
		this.hand.add(newCard);
	}

    
    @Override
    public String toString() {
        return String.valueOf(hand);
    }

    public static void main(String[] args) {
        Hand player = new Hand();
        CardDeck newDeck = new CardDeck();
        newDeck.shuffleDeck();
        newDeck.deal(player, 3);
        System.out.println(player);
    }
}
