package blackjack;
import java.util.ArrayList;
import java.util.Random;

public class CardDeck {
    private ArrayList<Card> cards = new ArrayList<Card>();

    public CardDeck(){
        char[] legalSuits = {'S','H','D','C'};
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                Card a = new Card(legalSuits[i], j + 1);
                cards.add(a);
            }
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

    public void deal(Hand hand, int n){
        for(int i = 0; i < n; i++){
            if (n > getCardCount()){
                throw new IllegalArgumentException("Not enough cards to deal");
            } else {
                Card newCard = this.getCard(getCardCount()-1);
			    this.cards.remove(newCard);
			    hand.addCard(newCard);
            }
        }
    }

    public void shuffleDeck(){
       ArrayList<Card> shuffledDeck = new ArrayList<Card>();
       Random random = new Random();

       while(shuffledDeck.size() < 52){
            int randInt = random.nextInt(getCardCount());
            shuffledDeck.add(getCard(randInt));
            cards.remove(randInt);
       }
       cards = shuffledDeck;
    }
    
    // used in test
    @Override
    public String toString() {
        return String.valueOf(cards);
    }
}