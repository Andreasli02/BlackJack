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

    private String suitToSymbol(char suit){
        if(suit == 'S')
            return "\u2660";
        if(suit == 'H')
            return "\u2665";
        if(suit == 'D')
            return "\u2666";
        if(suit == 'C')
            return "\u2663";
        return null;
    }

    private String faceToChar(int face){
        if(face == 1)
            return "A";
        if(face == 11)
            return "J";
        if(face == 12)
            return "Q";
        if(face == 13)
            return "K";
        return String.valueOf(face);
    }

    @Override
    public String toString() {
        return suitToSymbol(suit) + " " + faceToChar(face);
    }
}