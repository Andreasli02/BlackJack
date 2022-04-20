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

    public void dealHands(){
        player.getPlayerHand().clearHand();
        dealer.getDealerHand().clearHand();
        deck = new CardDeck();
        deck.shuffleDeck();

        deck.deal(player.getPlayerHand(), 2);
        deck.deal(dealer.getDealerHand(), 1);
    }
    
    // happends if you stand
    public void dealerTurn(){
        while(dealer.getDealerHand().getScore() < 17 && dealer.getDealerHand().getCardCount() < 7){
            deck.deal(dealer.getDealerHand(), 1);
        }
    }

    public void playerHit(){
        deck.deal(player.getPlayerHand(), 1);
    }

    public void playerDouble(){
        deck.deal(player.getPlayerHand(), 1);
        getPlayer().doubleBet();
        dealerTurn();
    }

    public void turnFinish(){
        if(player.getPlayerHand().isBust()){
            player.lose();
        } 
        if (player.getPlayerHand().isBlackjack() && !dealer.getDealerHand().isBlackjack()){
            player.blackjack();
        } 
        if (!player.getPlayerHand().isBust() && dealer.getDealerHand().isBust()){
            player.win();
        } 
        else{
            if (player.getPlayerHand().getScore() == dealer.getDealerHand().getScore()){
                player.tie();
            }
            if (player.getPlayerHand().getScore() > dealer.getDealerHand().getScore()){
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
        game.playerHit();
        game.dealerTurn();
        System.out.println(game);
        game.turnFinish();
        System.out.println(game);
    }
}