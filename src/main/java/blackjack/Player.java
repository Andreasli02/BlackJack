package blackjack;

public class Player {
    private int balance;
    private int betSize;
    private Hand playerHand;

    public Player(){
        playerHand = new Hand();
        balance = 2500;
    }

    public int getBalance(){
        return balance;
    }

    public int setBalance(int newBalance){
        return this.balance = newBalance;
    }

    public int getBetsize(){
        return betSize;
    }

    public Hand getHand(){
        return playerHand;
    }

    public void placeBetsize(int newBetSize){
        if (newBetSize < 0){
            throw new IllegalArgumentException("Bet size must be a positive number");
        }
        if (newBetSize > balance){
            throw new IllegalArgumentException("You cannot bet more than own");
        }
        betSize = newBetSize;
        balance -= newBetSize;
    }

    public void doubleBet(){
        balance -= betSize;
        this.betSize = betSize * 2;
    }

    public void win(){
        balance += 2 *betSize;
        betSize = 0;
    }

    public void tie(){
        balance += betSize;
        betSize = 0;
    }

    public void lose(){
        betSize = 0;
    }

    public void blackjack(){
        balance += 2.5 *betSize;
        betSize = 0;
    }

    @Override
    public String toString() {
        return "balance=" + String.valueOf(balance) + " betSize=" + String.valueOf(betSize) + " playerHand = " + String.valueOf(playerHand);
    }

    public static void main(String[] args) {
        Player Li = new Player();
        CardDeck newDeck = new CardDeck();
        newDeck.shuffleDeck();
        newDeck.deal(Li.getHand(), 3);
        System.out.println(Li);
        System.out.println(Li.getHand().getScore());
        Li.placeBetsize(2000);
        Li.lose();
        System.out.println(Li);
    }
}
