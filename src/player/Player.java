package player;
import java.util.ArrayList;

public class Player {
    
    // attribute
    private String name;          // Player's name
    private int score;            // Player's score
    private PlayerHand hand = new PlayerHand(); // Player's hand
    
    
    //method
    public Player(String name){
        this.name = name;  // Set player name
    }

    public String getName(){
        return this.name;  // Return player name
    }

    public int getScore() {
        return this.score; // Return score
    }

    public ArrayList<Card> getPlayerHand() {
        return this.hand.getPlayerHand(); // Get player's cards
    }
    
    public ArrayList<Card> PlayableCard(Card card, Color color) {
        ArrayList<Card> Phand = this.hand.getPlayerHand();   // All cards in hand
        ArrayList<Card> PlayableHand = new ArrayList();      // Cards that can be played
        
        for(Card i: Phand) {
            if(i.matches(card,color)) {  // Check if card is playable
                PlayableHand.add(i);
            }
        }   
        return PlayableHand;
    }
    
    public int playableHandSize(Card card, Color color){
        return this.PlayableCard(card,color).size(); // Number of playable cards
    }
    
    public boolean hasPlayableCard(Card card, Color color) {
        if(this.playableHandSize(card, color) > 0) {
            return true;  // At least one playable card
        }
        return false;
    }
    
    public PlayerHand getHand() {
        return this.hand;  // Return PlayerHand object
    }
    
    public void setScore(int score) {
        this.score = score; // Set score
    }

    public void incrementScore(int score){
        this.score += score; // Add to score
    }
    
    public boolean hasWon() {
        return hand.isEmpty(); // True if hand is empty
    }
}

