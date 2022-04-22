package blackjack;

public class Main {
    private CardDeck deck;
    private Player player;
    private Dealer dealer;

    public Main() {
        deck = new CardDeck();
        deck.shuffleDeck();
        player = new Player();
        dealer = new Dealer();

    }

    public Player getPlayer(){
        return player;
    }

    public Dealer getDealer(){
        return dealer;
    }

    public void dealHands(){
        player.getHand().clearHand();
        dealer.getHand().clearHand();
        deck = new CardDeck();
        deck.shuffleDeck();

        deck.deal(player.getHand(), 2);
        deck.deal(dealer.getHand(), 1);
    }
    
    public void dealerTurn(){
        while(dealer.getHand().getScore() < 17 && dealer.getHand().getCardCount() < 7){
            deck.deal(dealer.getHand(), 1);
        }
    }

    public void playerHit(){
        deck.deal(player.getHand(), 1);
    }

    // used exclusively for when player get blackjack
    public void dealerGetCard(){
        deck.deal(dealer.getHand(), 1);
    }

    public void playerDouble(){
        deck.deal(player.getHand(), 1);
        getPlayer().doubleBet();
        dealerTurn();
    }

    public void turnFinish(){
        Hand playerHand = player.getHand();
        Hand dealerHand = dealer.getHand();

        if(playerHand.isBust()){
            player.lose();
        } 
        if (playerHand.isBlackjack() && !dealerHand.isBlackjack()){
            player.blackjack();
        } 
        if (!playerHand.isBust() && dealerHand.isBust()){
            player.win();
        }
        else{
            if (playerHand.getScore() == dealerHand.getScore() && !playerHand.isBust()){
                player.tie();
            }
            if (playerHand.getScore() > dealerHand.getScore()){
                player.win();
            }
            else{
                player.lose();
            }
        }
    }
    

    @Override
    public String toString() {
        return String.valueOf(dealer) + " | " + String.valueOf(player);
    }

    public static void main(String[] args) {
        Main game = new Main();
        System.out.println(game);
        game.getPlayer().placeBetsize(500);
        System.out.println(game);
        game.dealHands();
        game.dealerTurn();
        System.out.println(game);
        game.turnFinish();
        System.out.println(game);
        System.out.println(game.getPlayer().getHand().getScore());
    }
}