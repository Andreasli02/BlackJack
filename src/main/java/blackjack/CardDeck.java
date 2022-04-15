package blackjack;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

public class CardDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public CardDeck(int n){
        if (n >= 0 && n <= 13){
            char[] f = {'S','H','D','C'};
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < n; j++){
                Card a = new Card(f[i], j + 1);
                cards.add(a);
            }
        }   
        }else{
            throw new IllegalArgumentException("illegal deck size");
        }
    }

    public int getCardCount(){
        return cards.size();
    }
    

    public Card getCard(int n){
        try{
            return cards.get(n);
        } catch (Exception e) {
            throw new IllegalArgumentException("This card doesn't exist");
        }
    }

    public void shufflePerfectly(){
        int middle = getCardCount()/2;
        for(int i = 0; i < middle; i++){
			cards.add(this.getCard(0));
			cards.remove(0);
			cards.add(this.getCard(middle-(i+1)));
			cards.remove(middle-(i+1));
		}
    }

    public void deal(Hand hand, int n){
        for(int i = 0; i < n; i++){
            if (n > getCardCount()){
                throw new IllegalArgumentException("Not enough cards to deal");
            } else {
                Card newCard = this.getCard(this.cards.size()-1);
			    this.cards.remove(newCard);
			    hand.addCard(newCard);
            }
        }
    }
    public boolean hasCard(Predicate<Card> predicate) {
		for (Card card: cards) {
			if (predicate.test(card)) {
				return true; 
			}
		}
		return false; 
	}
	
	public int getCardCount(Predicate<Card> predicate) {
		int count = 0; 
		for (Card card : cards) {
			if (predicate.test(card)) {
				count++;
			}
		}
		return count; 
	}
	
	public List<Card> getCards(Predicate<Card> predicate) {
		List<Card> matchingCards = new ArrayList<Card>();
		for (Card card : cards) {
			if (predicate.test(card)) {
				matchingCards.add(card);
			}
		}
		return matchingCards; 	
	}

    public Iterator<Card> iterator(){
        return this.cards.iterator();
    }
    
    public static void main(String[] args) {
        CardDeck kortstokk1 = new CardDeck(13);
        CardDeck Kortstokk2 = new CardDeck(4);
		System.out.println(kortstokk1.hasCard(c -> c.getSuit() == 'S' && c.getFace() == 12));
        System.out.println(Kortstokk2.hasCard(c -> c.getSuit() == 'S' && c.getFace() == 12));
        System.out.println(kortstokk1.getCardCount(c -> c.getSuit() == 'H'));
        System.out.println(kortstokk1.getCards(c -> c.getFace() == 1));
        
    }

}