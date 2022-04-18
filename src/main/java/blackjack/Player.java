package blackjack;

public class Player {
    private int balance;
    private int betSize;
    private Hand playerHand;

    public Player(){
        this.playerHand = new Hand();
        this.balance = 2500;
    }

    public int getBalance(){
        return this.balance;
    }

    public int setBalance(int newBalance){
        return this.balance = newBalance;
    }

    public int getBetsize(){
        return this.betSize;
    }

    public boolean PlayerWinHand(){
        return true;
    }

    public int updateBalance(){
        return 0;
    }

    @Override
    public String toString() {
        return "balance=" + String.valueOf(balance) + " playerHand = " + String.valueOf(playerHand);
    }

    public static void main(String[] args) {
        Player Li = new Player();
        System.out.println(Li);
    }
}
