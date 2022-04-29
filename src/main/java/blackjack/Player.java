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

    public int getBetsize(){
        return betSize;
    }

    public Hand getHand(){
        return playerHand;
    }

    public void placeBetsize(int newBetSize){
        if (newBetSize <= 0){
            throw new NumberFormatException("Bet size must be a positive number");
        }
        if (newBetSize > balance){
            throw new NumberFormatException("You cannot bet more than you own");
        }
        betSize = newBetSize;
        balance -= newBetSize;
    }

    public void doubleBet(){
        if(betSize * 2 > balance){
            throw new NumberFormatException("You cannot bet more than you own");
        }
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
        return "balance= " + String.valueOf(balance);
    }
}
