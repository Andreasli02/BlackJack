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

    public int getAceCount() {
		int aceCount = 0; 
		for (Card card : hand) {
			if (card.getFace() == 1)
				aceCount++;
		}
		return aceCount;
	}

    public int getScore(){
        int score = 0;
        int aceAmount = getAceCount();

        for (Card card : hand){
            if(card.getFace() > 10){
                score += 10;
            }
            else if(card.getFace() < 2){
                score += 11;
            }
            else{
                score += card.getFace();
            } 
            if(score > 21 && aceAmount != 0){
                score -= 10;
                aceAmount -= 1;
            }
        }
        return score;
    }

    public boolean isBust(){
        return getScore() > 21;
    }

    public boolean isBlackjack(){
        return getCardCount() == 2 && getScore() == 21;
    }

    public void addCard(Card newCard){
        if(getCardCount() >= 7){
            throw new IllegalArgumentException("Hand cannot have more than 7 cards");
        }
		hand.add(newCard);
	}

    public void clearHand(){
        hand.clear();
    }
}
