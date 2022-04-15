package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Card implements Comparable<Card> {
    private char kortfarge;
    private int tallverdi;

    public Card(char newKortfarge, int newTallverdi){
        if((newKortfarge == 'S') || (newKortfarge == 'H')||(newKortfarge == 'D')|| (newKortfarge == 'C')){
            this.kortfarge = newKortfarge;
        } else {
            throw new IllegalArgumentException("Suit cannot contain illegal letters.");
        }
        if (newTallverdi >= 1 && newTallverdi <= 13){
            this.tallverdi = newTallverdi;
        } else {
            throw new IllegalArgumentException("value plates must be between 1 and 13.");
        }
    }

    public char getSuit(){
        return kortfarge;
    }

    public int getFace(){
        return tallverdi;
    }



    @Override
    public String toString() {
        return kortfarge + String.valueOf(tallverdi);
    }

    @Override
    public int compareTo(Card o) {
		if (this.getSuit() == o.getSuit()) {
			if(this.getFace() == o.getFace()) {
				return  0;
			}
			else if(this.getFace() > o.getFace()) {
				return  1;
			}
			else if(this.getFace() < o.getFace()) {
				return  -1;
			}
		}
		else {
			if((this.getSuit() == 'C') ||(this.getSuit() == 'D' && ((o.getSuit()=='H') || (o.getSuit() == 'S'))) || (this.getSuit() == 'H' && o.getSuit() == 'S')) {
				return  -1;
			}
			else {
				return  1;
			}
		}
        return 0;
	}

    public static void main(String[] args) {
        ArrayList<Card> arr = new ArrayList<Card>();
        arr.add(new Card('S',2));
        arr.add(new Card('H',1));
        arr.add(new Card('S',1));
        arr.add(new Card('D',1));
        arr.add(new Card('S',13));
        arr.add(new Card('C',1));
        arr.add(new Card('S',10));

        System.out.println(arr);
        Collections.sort(arr);
        arr.stream().forEach(m -> System.out.println(m));
    }
    
}