package blackjack;

public class Card{
    private char suit;
    private int face;

    public Card(char newSuit, int newFace){
        if((newSuit == 'S') || (newSuit == 'H')||(newSuit == 'D')|| (newSuit == 'C')){
            this.suit = newSuit;
        } else {
            throw new IllegalArgumentException("Suit cannot contain illegal letters.");
        }
        if (newFace >= 1 && newFace <= 13){
            this.face = newFace;
        } else {
            throw new IllegalArgumentException("value must be between 1 and 13.");
        }
    }

    public char getSuit(){
        return suit;
    }

    public int getFace(){
        return face;
    }

    @Override
    public String toString() {
        return suit + String.valueOf(face);
    }

    public static void main(String[] args) {
		System.out.println("\u2665 This should be a Hearts suit symbol.");
		System.out.println("\u2666 This should be a Diamonds suit symbol.");
		System.out.println("\u2663 This should be a Clubs suit symbol.");
		System.out.println("\u2660 This should be a Spades suit symbol.");
	}
}